package com.topie.campus.tools.excel;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The <code>ExcelUtil</code> 与 {@link ExcelCell}搭配使用
 *
 * @author sargeras.wang
 * @version 1.0, Created at 2013年9月14日
 */
public class ExcelUtil {

    private static Logger LG = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 用来验证excel与Vo中的类型是否一致 <br>
     * Map<栏位类型,只能是哪些Cell类型>
     */
    private static Map<Class<?>, Integer[]> validateMap = new HashMap<Class<?>, Integer[]>();

    static {
        validateMap.put(String[].class, new Integer[] { Cell.CELL_TYPE_STRING });
        validateMap.put(Double[].class, new Integer[] { Cell.CELL_TYPE_NUMERIC });
        validateMap.put(String.class, new Integer[] { Cell.CELL_TYPE_STRING, Cell.CELL_TYPE_NUMERIC });
        validateMap.put(Double.class, new Integer[] { Cell.CELL_TYPE_NUMERIC });
        validateMap.put(Date.class, new Integer[] { Cell.CELL_TYPE_NUMERIC, Cell.CELL_TYPE_STRING });
        validateMap.put(Integer.class, new Integer[] { Cell.CELL_TYPE_NUMERIC });
        validateMap.put(Float.class, new Integer[] { Cell.CELL_TYPE_NUMERIC });
        validateMap.put(Long.class, new Integer[] { Cell.CELL_TYPE_NUMERIC });
        validateMap.put(Boolean.class, new Integer[] { Cell.CELL_TYPE_BOOLEAN });
    }

    /**
     * 获取cell类型的文字描述
     *
     * @param cellType <pre>
     *                                                                                                                                                                                                                                                                                                                                                                                                                 @return
     */
    private static String getCellTypeByInt(int cellType) {
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                return "Null type";
            case Cell.CELL_TYPE_BOOLEAN:
                return "Boolean type";
            case Cell.CELL_TYPE_ERROR:
                return "Error type";
            case Cell.CELL_TYPE_FORMULA:
                return "Formula type";
            case Cell.CELL_TYPE_NUMERIC:
                return "Numeric type";
            case Cell.CELL_TYPE_STRING:
                return "String type";
            default:
                return "Unknown type";
        }
    }

    /**
     * 获取单元格值
     */
    private static Object getCellValue(Cell cell) {
        if (cell == null || (cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils
                .isBlank(cell.getStringCellValue()))) {
            return null;
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_ERROR:
                return cell.getErrorCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return null;
        }
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于单个sheet
     *
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 javabean属性的数据类型有基本数据类型及String,Date,String[],Double[]
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public static <T> void exportXls(String[] headers, Collection<T> dataset, OutputStream out) {
        exportXls(headers, dataset, out, null);
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于单个sheet
     *
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的 javabean属性的数据类型有基本数据类型及String,Date,String[],Double[]
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public static <T> void exportXls(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();

        write2Sheet(sheet, headers, dataset, pattern);
        try {
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    public static <T> void exportExcelX(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        XSSFWorkbook workBook = new XSSFWorkbook();//创建工作薄;
        XSSFSheet sheet = workBook.createSheet();
        write2SheetX(sheet, headers, dataset, pattern);
        try {
            workBook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    public static <T> void exportExcelX(String[] headers, String[] mapHeaders, Collection<T> dataset, OutputStream out,
            String pattern) {
        XSSFWorkbook workBook = new XSSFWorkbook();//创建工作薄;
        XSSFSheet sheet = workBook.createSheet();
        write2SheetX(sheet, headers, mapHeaders, dataset, pattern);
        try {
            workBook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 用于多个sheet
     */
    public static <T> void exportXls(List<String> sheetNames, List<String[]> headers, List<Collection<T>> dataSets,
            OutputStream out, String pattern) {
        if (sheetNames.size() != headers.size() || headers.size() != dataSets.size()) {
            LG.error("工作栏、表头和数据不一致！");
            throw new RuntimeException("工作栏、表头和数据不一致！");
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (int i = 0; i < sheetNames.size(); i++) {
            workbook.createSheet(sheetNames.get(i));
        }
        for (int i = 0; i < sheetNames.size(); i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            write2Sheet(sheet, headers.get(i), dataSets.get(i), pattern);
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    public static <T> void exportXlsx(List<String> sheetNames, List<String[]> headers, List<Collection<T>> dataSets,
            OutputStream out, String pattern) {
        if (sheetNames.size() != headers.size() || headers.size() != dataSets.size()) {
            LG.error("工作栏、表头和数据不一致！");
            throw new RuntimeException("工作栏、表头和数据不一致！");
        }
        XSSFWorkbook workBook = new XSSFWorkbook();//创建工作薄;

        for (int i = 0; i < sheetNames.size(); i++) {
            workBook.createSheet(sheetNames.get(i));
        }
        for (int i = 0; i < sheetNames.size(); i++) {
            XSSFSheet sheet = workBook.getSheetAt(i);
            write2SheetX(sheet, headers.get(i), dataSets.get(i), pattern);
        }
        try {
            workBook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    public static <T> void exportXlsx(List<String> sheetNames, List<String[]> mapHeaders, List<String[]> headers,
            List<Collection<T>> dataSets, OutputStream out, String pattern) {
        if (sheetNames.size() != headers.size() || headers.size() != dataSets.size()) {
            LG.error("工作栏、表头和数据不一致！");
            throw new RuntimeException("工作栏、表头和数据不一致！");
        }
        XSSFWorkbook workBook = new XSSFWorkbook();//创建工作薄;

        for (int i = 0; i < sheetNames.size(); i++) {
            workBook.createSheet(sheetNames.get(i));
        }
        for (int i = 0; i < sheetNames.size(); i++) {
            XSSFSheet sheet = workBook.getSheetAt(i);
            write2SheetX(sheet, headers.get(i), mapHeaders.get(i), dataSets.get(i), pattern);
        }
        try {
            workBook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于多个sheet
     *
     * @param sheets {@link ExcelSheet}的集合
     * @param out    与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public static <T> void exportXls(List<ExcelSheet<T>> sheets, OutputStream out) {
        exportXls(sheets, out, null);
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于多个sheet
     *
     * @param sheets  {@link ExcelSheet}的集合
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public static <T> void exportXls(List<ExcelSheet<T>> sheets, OutputStream out, String pattern) {
        if (CollectionUtils.isEmpty(sheets)) {
            return;
        }
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExcelSheet<T> sheet : sheets) {
            // 生成一个表格
            HSSFSheet hssfSheet = workbook.createSheet(sheet.getSheetName());
            write2Sheet(hssfSheet, sheet.getHeaders(), sheet.getDataset(), pattern);
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 每个sheet的写入
     *
     * @param sheet   页签
     * @param headers 表头
     * @param dataset 数据集合
     * @param pattern 日期格式
     */
    private static <T> void write2Sheet(HSSFSheet sheet, String[] headers, Collection<T> dataset, String pattern) {
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            try {
                if (t instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) t;
                    int cellNum = 0;
                    for (String k : headers) {
                        if (map.containsKey(k) == false) {
                            LG.error("Map 中 不存在 key [" + k + "]");
                            continue;
                        }
                        Object value = map.get(k);
                        HSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(HSSFCellStyle.ALIGN_LEFT);
                        cell.setCellValue(String.valueOf(value));
                        cellNum++;
                    }
                } else {
                    List<Object> annotationItems = sortFieldAndMethodByAnno(t.getClass());
                    int cellNum = 0;
                    for (int i = 0; i < annotationItems.size(); i++) {
                        HSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(HSSFCellStyle.ALIGN_LEFT);
                        Object value;
                        Object annoTargetType = null;
                        if (annotationItems.get(i) instanceof FieldForSorting) {
                            Field field = ((FieldForSorting) annotationItems.get(i)).getField();
                            annoTargetType = field;
                            field.setAccessible(true);
                            value = field.get(t);
                        } else {
                            Method method = ((MethodForSorting) annotationItems.get(i)).getMethod();
                            annoTargetType = method;
                            method.setAccessible(true);
                            value = method.invoke(t);
                        }
                        String textValue = null;
                        if (value instanceof Integer) {
                            int intValue = (Integer) value;
                            cell.setCellValue(intValue);
                        } else if (value instanceof Float) {
                            float fValue = (Float) value;
                            cell.setCellValue(fValue);
                        } else if (value instanceof Double) {
                            double dValue = (Double) value;
                            cell.setCellValue(dValue);
                        } else if (value instanceof Long) {
                            long longValue = (Long) value;
                            cell.setCellValue(longValue);
                        } else if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            cell.setCellValue(bValue);
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof String[]) {
                            String[] strArr = (String[]) value;
                            for (int j = 0; j < strArr.length; j++) {
                                String str = strArr[j];
                                cell.setCellValue(str);
                                if (j != strArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else if (value instanceof Double[]) {
                            Double[] douArr = (Double[]) value;
                            for (int j = 0; j < douArr.length; j++) {
                                Double val = douArr[j];
                                // 资料不为空则set Value
                                if (val != null) {
                                    cell.setCellValue(val);
                                }

                                if (j != douArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            String empty = StringUtils.EMPTY;
                            if (annoTargetType instanceof Field) {
                                ExcelCell anno = ((Field) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            } else {
                                ExcelCell anno = ((Method) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            }
                        }
                        if (textValue != null) {
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }

                        cellNum++;
                    }
                }
            } catch (Exception e) {
                LG.error(e.toString(), e);
            }
        }
        // 设定自动宽度
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //调整默认宽度
        int curColWidth = 0;
        int defaultColWidth = 2000;
        for (int i = 0; i < headers.length; i++) {
            curColWidth = sheet.getColumnWidth(i);
            if (curColWidth < defaultColWidth) {
                sheet.setColumnWidth(i, defaultColWidth);
            }
        }
    }

    /**
     * 写入excel2007
     */
    private static <T> void write2SheetX(XSSFSheet sheet, String[] headers, Collection<T> dataset, String pattern) {
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        if (dataset == null) {
            return;
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            try {
                if (t instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) t;
                    int cellNum = 0;
                    for (String k : headers) {
                        if (map.containsKey(k) == false) {
                            LG.error("Map 中 不存在 key [" + k + "]");
                            continue;
                        }
                        Object value = map.get(k);
                        XSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(XSSFCellStyle.ALIGN_LEFT);
                        cell.setCellValue(String.valueOf(value));
                        cellNum++;
                    }
                } else {
                    List<Object> annotationItems = sortFieldAndMethodByAnno(t.getClass());
                    int cellNum = 0;
                    for (int i = 0; i < annotationItems.size(); i++) {
                        XSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(XSSFCellStyle.ALIGN_LEFT);
                        Object value;
                        Object annoTargetType = null;
                        if (annotationItems.get(i) instanceof FieldForSorting) {
                            Field field = ((FieldForSorting) annotationItems.get(i)).getField();
                            annoTargetType = field;
                            field.setAccessible(true);
                            value = field.get(t);
                        } else {
                            Method method = ((MethodForSorting) annotationItems.get(i)).getMethod();
                            annoTargetType = method;
                            method.setAccessible(true);
                            value = method.invoke(t);
                        }
                        String textValue = null;
                        if (value instanceof Integer) {
                            int intValue = (Integer) value;
                            cell.setCellValue(intValue);
                        } else if (value instanceof Float) {
                            float fValue = (Float) value;
                            cell.setCellValue(fValue);
                        } else if (value instanceof Double) {
                            double dValue = (Double) value;
                            cell.setCellValue(dValue);
                        } else if (value instanceof Long) {
                            long longValue = (Long) value;
                            cell.setCellValue(longValue);
                        } else if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            cell.setCellValue(bValue);
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof String[]) {
                            String[] strArr = (String[]) value;
                            for (int j = 0; j < strArr.length; j++) {
                                String str = strArr[j];
                                cell.setCellValue(str);
                                if (j != strArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else if (value instanceof Double[]) {
                            Double[] douArr = (Double[]) value;
                            for (int j = 0; j < douArr.length; j++) {
                                Double val = douArr[j];
                                // 资料不为空则set Value
                                if (val != null) {
                                    cell.setCellValue(val);
                                }

                                if (j != douArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            String empty = StringUtils.EMPTY;
                            if (annoTargetType instanceof Field) {
                                ExcelCell anno = ((Field) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            } else {
                                ExcelCell anno = ((Method) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            }
                        }
                        if (textValue != null) {
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }

                        cellNum++;
                    }
                }
            } catch (Exception e) {
                LG.error(e.toString(), e);
            }
        }
        // 设定自动宽度
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //调整默认宽度
        int curColWidth = 0;
        int defaultColWidth = 2000;
        for (int i = 0; i < headers.length; i++) {
            curColWidth = sheet.getColumnWidth(i);
            if (curColWidth < defaultColWidth) {
                sheet.setColumnWidth(i, defaultColWidth);
            }
        }
    }

    private static <T> void write2SheetX(XSSFSheet sheet, String[] headers, String[] mapHeaders, Collection<T> dataset,
            String pattern) {
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        headers = mapHeaders;
        if (dataset == null) {
            return;
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            try {
                if (t instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) t;
                    int cellNum = 0;
                    for (String k : headers) {
                        if (map.containsKey(k) == false) {
                            LG.error("Map 中 不存在 key [" + k + "]");
                            continue;
                        }
                        Object value = map.get(k);
                        XSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(XSSFCellStyle.ALIGN_LEFT);
                        cell.setCellValue(String.valueOf(value));
                        cellNum++;
                    }
                } else {
                    List<Object> annotationItems = sortFieldAndMethodByAnno(t.getClass());
                    int cellNum = 0;
                    for (int i = 0; i < annotationItems.size(); i++) {
                        XSSFCell cell = row.createCell(cellNum);
                        cell.setCellType(XSSFCellStyle.ALIGN_LEFT);
                        Object value;
                        Object annoTargetType = null;
                        if (annotationItems.get(i) instanceof FieldForSorting) {
                            Field field = ((FieldForSorting) annotationItems.get(i)).getField();
                            annoTargetType = field;
                            field.setAccessible(true);
                            value = field.get(t);
                        } else {
                            Method method = ((MethodForSorting) annotationItems.get(i)).getMethod();
                            annoTargetType = method;
                            method.setAccessible(true);
                            value = method.invoke(t);
                        }
                        String textValue = null;
                        if (value instanceof Integer) {
                            int intValue = (Integer) value;
                            cell.setCellValue(intValue);
                        } else if (value instanceof Float) {
                            float fValue = (Float) value;
                            cell.setCellValue(fValue);
                        } else if (value instanceof Double) {
                            double dValue = (Double) value;
                            cell.setCellValue(dValue);
                        } else if (value instanceof Long) {
                            long longValue = (Long) value;
                            cell.setCellValue(longValue);
                        } else if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            cell.setCellValue(bValue);
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof String[]) {
                            String[] strArr = (String[]) value;
                            for (int j = 0; j < strArr.length; j++) {
                                String str = strArr[j];
                                cell.setCellValue(str);
                                if (j != strArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else if (value instanceof Double[]) {
                            Double[] douArr = (Double[]) value;
                            for (int j = 0; j < douArr.length; j++) {
                                Double val = douArr[j];
                                // 资料不为空则set Value
                                if (val != null) {
                                    cell.setCellValue(val);
                                }

                                if (j != douArr.length - 1) {
                                    cellNum++;
                                    cell = row.createCell(cellNum);
                                }
                            }
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            String empty = StringUtils.EMPTY;
                            if (annoTargetType instanceof Field) {
                                ExcelCell anno = ((Field) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            } else {
                                ExcelCell anno = ((Method) annoTargetType).getAnnotation(ExcelCell.class);
                                if (anno != null) {
                                    empty = anno.defaultValue();
                                }
                                textValue = value == null ? empty : value.toString();
                            }
                        }
                        if (textValue != null) {
                            XSSFRichTextString richString = new XSSFRichTextString(textValue);
                            cell.setCellValue(richString);
                        }

                        cellNum++;
                    }
                }
            } catch (Exception e) {
                LG.error(e.toString(), e);
            }
        }
        // 设定自动宽度
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //调整默认宽度
        int curColWidth = 0;
        int defaultColWidth = 2000;
        for (int i = 0; i < headers.length; i++) {
            curColWidth = sheet.getColumnWidth(i);
            if (curColWidth < defaultColWidth) {
                sheet.setColumnWidth(i, defaultColWidth);
            }
        }
    }

    /**
     * 把Excel的数据封装成voList
     *
     * @param clazz       vo的Class
     * @param inputStream excel输入流
     * @param pattern     如果有时间数据，设定输入格式。默认为"yyy-MM-dd"
     * @param logs        错误log集合
     * @param arrayCount  如果vo中有数组类型,那就按照index顺序,把数组应该有几个值写上.
     * @return voList
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> importExcel(Class<T> clazz, InputStream inputStream, String pattern, ExcelLogs logs,
            Integer... arrayCount) {
        HSSFWorkbook workBook = null;
        try {
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
        List<T> list = new ArrayList<T>();
        HSSFSheet sheet = workBook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        try {
            List<ExcelLog> logList = new ArrayList<ExcelLog>();
            // Map<title,index>
            Map<String, Integer> titleMap = new HashMap<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    if (clazz == Map.class) {
                        // 解析map用的key,就是excel标题行
                        Iterator<Cell> cellIterator = row.cellIterator();
                        Integer index = 0;
                        while (cellIterator.hasNext()) {
                            String value = cellIterator.next().getStringCellValue();
                            titleMap.put(value, index);
                            index++;
                        }
                    }
                    continue;
                }
                // 整行都空，就跳过
                boolean allRowIsNull = true;
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Object cellValue = getCellValue(cellIterator.next());
                    if (cellValue != null) {
                        allRowIsNull = false;
                        break;
                    }
                }
                if (allRowIsNull) {
                    LG.warn("Excel row " + row.getRowNum() + " all row value is null!");
                    continue;
                }
                T t = null;
                StringBuilder log = new StringBuilder();
                if (clazz == Map.class) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (String k : titleMap.keySet()) {
                        Integer index = titleMap.get(k);
                        String value = row.getCell(index).getStringCellValue();
                        map.put(k, value);
                    }
                    list.add((T) map);
                } else {
                    t = clazz.newInstance();
                    int arrayIndex = 0;// 标识当前第几个数组了
                    int cellIndex = 0;// 标识当前读到这一行的第几个cell了
                    List<FieldForSorting> fields = sortFieldByAnno(clazz);
                    for (FieldForSorting ffs : fields) {
                        Field field = ffs.getField();
                        field.setAccessible(true);
                        if (field.getType().isArray()) {
                            Integer count = arrayCount[arrayIndex];
                            Object[] value = null;
                            if (field.getType().equals(String[].class)) {
                                value = new String[count];
                            } else {
                                // 目前只支持String[]和Double[]
                                value = new Double[count];
                            }
                            for (int i = 0; i < count; i++) {
                                Cell cell = row.getCell(cellIndex);
                                String errMsg = validateCell(cell, field, cellIndex);
                                if (StringUtils.isBlank(errMsg)) {
                                    value[i] = getCellValue(cell);
                                } else {
                                    log.append(errMsg);
                                    log.append(";");
                                    logs.setHasError(true);
                                }
                                cellIndex++;
                            }
                            field.set(t, value);
                            arrayIndex++;
                        } else {
                            Cell cell = row.getCell(cellIndex);
                            String errMsg = validateCell(cell, field, cellIndex);
                            if (StringUtils.isBlank(errMsg)) {
                                Object value = null;
                                // 处理特殊情况,Excel中的String,转换成Bean的Date
                                if (field.getType().equals(Date.class) && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                    Object strDate = getCellValue(cell);
                                    try {
                                        value = new SimpleDateFormat(pattern).parse(strDate.toString());
                                    } catch (ParseException e) {

                                        errMsg = MessageFormat.format("the cell [{0}] can not be converted to a date ",
                                                CellReference.convertNumToColString(cell.getColumnIndex()));
                                    }
                                } else {
                                    value = getCellValue(cell);
                                    // 处理特殊情况,excel的value为String,且bean中为其他,且defaultValue不为空,那就=defaultValue
                                    ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
                                    if (value instanceof String && !field.getType().equals(String.class) && StringUtils
                                            .isNotBlank(annoCell.defaultValue())) {
                                        value = annoCell.defaultValue();
                                    }
                                }
                                field.set(t, value);
                            }
                            if (StringUtils.isNotBlank(errMsg)) {
                                log.append(errMsg);
                                log.append(";");
                                logs.setHasError(true);
                            }
                            cellIndex++;
                        }
                    }
                    list.add(t);
                    logList.add(new ExcelLog(t, log.toString(), row.getRowNum() + 1));
                }
            }
            logs.setLogList(logList);
        } catch (InstantiationException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        }
        return list;
    }

    /**
     * 把Excel的数据封装成voList
     *
     * @param clazz       vo的Class
     * @param inputStream excel输入流
     * @param sheetNum    指定excel的sheet
     * @param pattern     如果有时间数据，设定输入格式。默认为"yyy-MM-dd"
     * @param logs        错误log集合
     * @param arrayCount  如果vo中有数组类型,那就按照index顺序,把数组应该有几个值写上.
     * @return voList
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> importExcel(Class<T> clazz, InputStream inputStream, Integer sheetNum,
            String pattern, ExcelLogs logs, Integer... arrayCount) {
        HSSFWorkbook workBook = null;
        try {
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
        List<T> list = new ArrayList<T>();
        HSSFSheet sheet = workBook.getSheetAt(sheetNum);
        Iterator<Row> rowIterator = sheet.rowIterator();
        try {
            List<ExcelLog> logList = new ArrayList<ExcelLog>();
            // Map<title,index>
            Map<String, Integer> titleMap = new HashMap<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    if (clazz == Map.class) {
                        // 解析map用的key,就是excel标题行
                        Iterator<Cell> cellIterator = row.cellIterator();
                        Integer index = 0;
                        while (cellIterator.hasNext()) {
                            String value = cellIterator.next().getStringCellValue();
                            titleMap.put(value, index);
                            index++;
                        }
                    }
                    continue;
                }
                // 整行都空，就跳过
                boolean allRowIsNull = true;
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Object cellValue = getCellValue(cellIterator.next());
                    if (cellValue != null) {
                        allRowIsNull = false;
                        break;
                    }
                }
                if (allRowIsNull) {
                    LG.warn("Excel row " + row.getRowNum() + " all row value is null!");
                    continue;
                }
                T t = null;
                StringBuilder log = new StringBuilder();
                if (clazz == Map.class) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (String k : titleMap.keySet()) {
                        Integer index = titleMap.get(k);
                        String value = row.getCell(index).getStringCellValue();
                        map.put(k, value);
                    }
                    list.add((T) map);
                } else {
                    t = clazz.newInstance();
                    int arrayIndex = 0;// 标识当前第几个数组了
                    int cellIndex = 0;// 标识当前读到这一行的第几个cell了
                    List<FieldForSorting> fields = sortFieldByAnno(clazz);
                    for (FieldForSorting ffs : fields) {
                        Field field = ffs.getField();
                        field.setAccessible(true);
                        if (field.getType().isArray()) {
                            Integer count = arrayCount[arrayIndex];
                            Object[] value = null;
                            if (field.getType().equals(String[].class)) {
                                value = new String[count];
                            } else {
                                // 目前只支持String[]和Double[]
                                value = new Double[count];
                            }
                            for (int i = 0; i < count; i++) {
                               // Cell cell = row.getCell(cellIndex);
                            	Cell cell = row.getCell(ffs.getIndex());
                                String errMsg = validateCell(cell, field, cellIndex);
                                if (StringUtils.isBlank(errMsg)) {
                                    value[i] = getCellValue(cell);
                                } else {
                                    log.append(errMsg);
                                    log.append(";");
                                    logs.setHasError(true);
                                }
                                cellIndex++;
                            }
                            field.set(t, value);
                            arrayIndex++;
                        } else {
                            //Cell cell = row.getCell(cellIndex);
                        	Cell cell = row.getCell(ffs.getIndex());
                            String errMsg = validateCell(cell, field, cellIndex);
                            if (StringUtils.isBlank(errMsg)) {
                                Object value = null;
                                // 处理特殊情况,Excel中的String,转换成Bean的Date
                                if (field.getType().equals(Date.class) && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                    Object strDate = getCellValue(cell);
                                    try {
                                        value = new SimpleDateFormat(pattern).parse(strDate.toString());
                                    } catch (ParseException e) {

                                        errMsg = MessageFormat.format("the cell [{0}] can not be converted to a date ",
                                                CellReference.convertNumToColString(cell.getColumnIndex()));
                                    }
                                } else {
                                    value = getCellValue(cell);
                                    // 处理特殊情况,excel的value为String,且bean中为其他,且defaultValue不为空,那就=defaultValue
                                    ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
                                    if (value instanceof String && !field.getType().equals(String.class) && StringUtils
                                            .isNotBlank(annoCell.defaultValue())) {
                                        value = annoCell.defaultValue();
                                    }
                                }
                                System.out.println(field.getName()+"--"+value.getClass().getTypeName());
                                field.set(t, value);
                            }
                            if (StringUtils.isNotBlank(errMsg)) {
                                log.append(errMsg);
                                log.append(";");
                                logs.setHasError(true);
                            }
                            cellIndex++;
                        }
                    }
                    list.add(t);
                    logList.add(new ExcelLog(t, log.toString(), row.getRowNum() + 1));
                }
            }
            logs.setLogList(logList);
        } catch (InstantiationException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        }
        return list;
    }

    public static <T> Collection<T> importExcelX(Class<T> clazz, InputStream inputStream, Integer sheetNum,
            String pattern, ExcelLogs logs, Integer... arrayCount) {
        XSSFWorkbook workBook = null;
        try {
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
        List<T> list = new ArrayList<T>();
        XSSFSheet sheet = workBook.getSheetAt(sheetNum);
        Iterator<Row> rowIterator = sheet.rowIterator();
        try {
            List<ExcelLog> logList = new ArrayList<ExcelLog>();
            // Map<title,index>
            Map<String, Integer> titleMap = new HashMap<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    if (clazz == Map.class) {
                        // 解析map用的key,就是excel标题行
                        Iterator<Cell> cellIterator = row.cellIterator();
                        Integer index = 0;
                        while (cellIterator.hasNext()) {
                            String value = cellIterator.next().getStringCellValue();
                            titleMap.put(value, index);
                            index++;
                        }
                    }
                    continue;
                }
                // 整行都空，就跳过
                boolean allRowIsNull = true;
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Object cellValue = getCellValue(cellIterator.next());
                    if (cellValue != null) {
                        allRowIsNull = false;
                        break;
                    }
                }
                if (allRowIsNull) {
                    LG.warn("Excel row " + row.getRowNum() + " all row value is null!");
                    continue;
                }
                T t = null;
                StringBuilder log = new StringBuilder();
                if (clazz == Map.class) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    for (String k : titleMap.keySet()) {
                        Integer index = titleMap.get(k);
                        Object value = getCellData(row.getCell(index));
                        map.put(k, value);
                    }
                    list.add((T) map);
                } else {
                    t = clazz.newInstance();
                    int arrayIndex = 0;// 标识当前第几个数组了
                    int cellIndex = 0;// 标识当前读到这一行的第几个cell了
                    List<FieldForSorting> fields = sortFieldByAnno(clazz);
                    for (FieldForSorting ffs : fields) {
                        Field field = ffs.getField();
                        field.setAccessible(true);
                        if (field.getType().isArray()) {
                            Integer count = arrayCount[arrayIndex];
                            Object[] value = null;
                            if (field.getType().equals(String[].class)) {
                                value = new String[count];
                            } else {
                                // 目前只支持String[]和Double[]
                                value = new Double[count];
                            }
                            for (int i = 0; i < count; i++) {
                                //Cell cell = row.getCell(cellIndex);
                            	Cell cell = row.getCell(ffs.getIndex());
                                String errMsg = validateCell(cell, field, cellIndex);
                                if (StringUtils.isBlank(errMsg)) {
                                    value[i] = getCellValue(cell);
                                } else {
                                    log.append(errMsg);
                                    log.append(";");
                                    logs.setHasError(true);
                                }
                                cellIndex++;
                            }
                            field.set(t, value);
                            arrayIndex++;
                        } else {
                            //Cell cell = row.getCell(cellIndex);
                        	Cell cell = row.getCell(ffs.getIndex());
                            String errMsg = validateCell(cell, field, cellIndex);
                            if (StringUtils.isBlank(errMsg)) {
                                Object value = null;
                                // 处理特殊情况,Excel中的String,转换成Bean的Date
                                if (field.getType().equals(Date.class) && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                    Object strDate = getCellValue(cell);
                                    try {
                                        value = new SimpleDateFormat(pattern).parse(strDate.toString());
                                    } catch (ParseException e) {

                                        errMsg = MessageFormat.format("the cell [{0}] can not be converted to a date ",
                                                CellReference.convertNumToColString(cell.getColumnIndex()));
                                    }
                                } else {
                                    value = getCellValue(cell);
                                    // 处理特殊情况,excel的value为String,且bean中为其他,且defaultValue不为空,那就=defaultValue
                                    ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
                                    if (value instanceof String && !field.getType().equals(String.class) && StringUtils
                                            .isNotBlank(annoCell.defaultValue())) {
                                        value = annoCell.defaultValue();
                                    }
                                }
                                if (value != null && field.getType().equals(String.class) && value.getClass()
                                        .equals(Double.class)) {
                                    String strValue = "";
                                    Double doubleValue = (Double) value;
                                    if (doubleValue % 1.0 == 0) {
                                        strValue = String.valueOf((doubleValue.longValue()));
                                    } else {
                                        strValue = String.valueOf(value);
                                    } field.set(t, strValue);
                                } else {
                                    field.set(t, value);
                                }
                            } if (StringUtils.isNotBlank(errMsg)) {
                                log.append(errMsg);
                                log.append(";");
                                logs.setHasError(true);
                            }
                            cellIndex++;
                        }
                    } list.add(t);
                    logList.add(new ExcelLog(t, log.toString(), row.getRowNum() + 1));
                }
            } logs.setLogList(logList);
        } catch (InstantiationException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}", clazz.getSimpleName()), e);
        } return list;
    }

    public static <T> Collection<T> importExcelX(Class<T> clazz, InputStream inputStream, String pattern,
            ExcelLogs logs, Integer... arrayCount) {
        return importExcelX(clazz, inputStream, 0, pattern, logs, arrayCount);
    }

    /**
     * 驗證Cell類型是否正確
     *
     * @param cell    cell單元格
     * @param field   欄位
     * @param cellNum 第幾個欄位,用於errMsg
     */
    private static String validateCell(Cell cell, Field field, int cellNum) {
        String columnName = CellReference.convertNumToColString(cellNum);
        String result = null;
        Integer[] integers = validateMap.get(field.getType());
        if (integers == null) {
            result = MessageFormat.format("Unsupported type [{0}]", field.getType().getSimpleName());
            return result;
        }
        ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
        if (cell == null || (cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils
                .isBlank(cell.getStringCellValue()))) {
            if (annoCell != null && annoCell.valid().allowNull() == false) {
                result = MessageFormat.format("the cell [{0}] can not null", columnName);
            }
            ;
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK && annoCell.valid().allowNull()) {
            return result;
        } else {
            List<Integer> cellTypes = Arrays.asList(integers);

            // 如果類型不在指定範圍內,並且沒有默認值
            if (!(cellTypes.contains(cell.getCellType()))
                    || StringUtils.isNotBlank(annoCell.defaultValue()) && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                StringBuilder strType = new StringBuilder();
                for (int i = 0; i < cellTypes.size(); i++) {
                    Integer intType = cellTypes.get(i);
                    strType.append(getCellTypeByInt(intType));
                    if (i != cellTypes.size() - 1) {
                        strType.append(",");
                    }
                }
                result = MessageFormat.format("the cell [{0}] type must [{1}]", columnName, strType.toString());
            } else {
                // 类型符合验证,但值不在要求范围内的
                // String in
                if (annoCell.valid().in().length != 0 && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    String[] in = annoCell.valid().in();
                    String cellValue = cell.getStringCellValue();
                    boolean isIn = false;
                    for (String str : in) {
                        if (str.equals(cellValue)) {
                            isIn = true;
                        }
                    }
                    if (!isIn) {
                        result = MessageFormat.format("the cell [{0}] value must in {1}", columnName, in);
                    }
                }
                // 数字型
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    double cellValue = cell.getNumericCellValue();
                    // 小于
                    if (!Double.isNaN(annoCell.valid().lt())) {
                        if (!(cellValue < annoCell.valid().lt())) {
                            result = MessageFormat.format("the cell [{0}] value must less than [{1}]", columnName,
                                    annoCell.valid().lt());
                        }
                    }
                    // 大于
                    if (!Double.isNaN(annoCell.valid().gt())) {
                        if (!(cellValue > annoCell.valid().gt())) {
                            result = MessageFormat.format("the cell [{0}] value must greater than [{1}]", columnName,
                                    annoCell.valid().gt());
                        }
                    }
                    // 小于等于
                    if (!Double.isNaN(annoCell.valid().le())) {
                        if (!(cellValue <= annoCell.valid().le())) {
                            result = MessageFormat
                                    .format("the cell [{0}] value must less than or equal [{1}]", columnName,
                                            annoCell.valid().le());
                        }
                    }
                    // 大于等于
                    if (!Double.isNaN(annoCell.valid().ge())) {
                        if (!(cellValue >= annoCell.valid().ge())) {
                            result = MessageFormat
                                    .format("the cell [{0}] value must greater than or equal [{1}]", columnName,
                                            annoCell.valid().ge());
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据annotation的seq排序后的栏位
     */
    private static List<FieldForSorting> sortFieldByAnno(Class<?> clazz) {
        Field[] fieldsArr = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        List<FieldForSorting> fields = new ArrayList<FieldForSorting>();
        for (Field field : fieldsArr) {
            ExcelCell ec = field.getAnnotation(ExcelCell.class);
            if (ec == null) {
                // 没有ExcelCell Annotation 视为不汇入
                continue;
            }
            int id = ec.index();
            fields.add(new FieldForSorting(field, id));
        }
        sortByProperties(fields, true, false, "index");
        return fields;
    }

    private static List<Object> sortFieldAndMethodByAnno(Class<?> clazz) {
        Field[] fieldsArr = clazz.getDeclaredFields();
        Method[] methods = clazz.getDeclaredMethods();
        List<Object> fieldAndMethods = new ArrayList<Object>();
        for (Field field : fieldsArr) {
            ExcelCell ec = field.getAnnotation(ExcelCell.class);
            if (ec == null) {
                // 没有ExcelCell Annotation 视为不汇入
                continue;
            }
            int id = ec.index();
            fieldAndMethods.add(new FieldForSorting(field, id));
        }
        for (Method method : methods) {
            ExcelCell ec = method.getAnnotation(ExcelCell.class);
            if (ec == null) {
                // 没有ExcelCell Annotation 视为不汇入
                continue;
            }
            int id = ec.index();
            fieldAndMethods.add(new MethodForSorting(method, id));
        }
        sortByProperties(fieldAndMethods, true, false, "index");
        return fieldAndMethods;
    }

    @SuppressWarnings("unchecked")
    private static void sortByProperties(List<? extends Object> list, boolean isNullHigh, boolean isReversed,
            String... props) {
        if (CollectionUtils.isNotEmpty(list)) {
            Comparator<?> typeComp = ComparableComparator.getInstance();
            if (isNullHigh == true) {
                typeComp = ComparatorUtils.nullHighComparator(typeComp);
            } else {
                typeComp = ComparatorUtils.nullLowComparator(typeComp);
            }
            if (isReversed) {
                typeComp = ComparatorUtils.reversedComparator(typeComp);
            }

            List<Object> sortCols = new ArrayList<Object>();

            if (props != null) {
                for (String prop : props) {
                    sortCols.add(new BeanComparator(prop, typeComp));
                }
            }
            if (sortCols.size() > 0) {
                Comparator<Object> sortChain = new ComparatorChain(sortCols);
                Collections.sort(list, sortChain);
            }
        }
    }

    private static Object getCellData(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

}