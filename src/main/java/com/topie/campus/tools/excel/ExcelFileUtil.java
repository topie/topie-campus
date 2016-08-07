package com.topie.campus.tools.excel;

import com.topie.campus.common.utils.PropertiesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cgj on 2016/1/6.
 */
public class ExcelFileUtil {

    public static <T> void reponseXls(HttpServletResponse response, String fileName,
                                      String[] headers, List<T> list) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-core");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        InputStream inputStream = getXlsInputStreamByBean(headers, list);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    }

    public static <T> void reponseXlsx(HttpServletResponse response, String fileName,
                                       String[] headers, List<T> list) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-core");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        InputStream inputStream = getXlsxInputStreamByBean(headers, list);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    }

    public static <T> void reponseXlsx(HttpServletResponse response, String fileName,
                                       List<String> sheetNames, List<String[]> headers, List<Collection<T>> list)
            throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-core");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        InputStream inputStream = getXlsxInputStreamByBean(sheetNames, headers, list);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    }

    public static <T> void reponseXlsx(HttpServletResponse response, String fileName,
                                       List<String> sheetNames, List<String[]> mapHeaders, List<String[]> headers,
                                       List<Collection<T>> list) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-core");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        InputStream inputStream = getXlsxInputStreamByBean(sheetNames, mapHeaders, headers, list);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    }

    public static <T> void reponseXlsx(HttpServletResponse response, String fileName,
                                       String[] headers, String[] mapHeaders, List<T> list) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-core");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        InputStream inputStream = getXlsxInputStreamByBean(headers, mapHeaders, list);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    }

    public static InputStream getXlsInputStream(String[] headers, List<Map> list)
            throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportXls(headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsxInputStreamByBean(String[] headers, List<T> list)
            throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportExcelX(headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsxInputStreamByBean(String[] headers, List<T> list, String filePath)
            throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportExcelX(headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsxInputStreamByBean(List<String> sheetNames,
                                                           List<String[]> headers, List<Collection<T>> list) throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportXlsx(sheetNames, headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsxInputStreamByBean(List<String> sheetNames,
                                                           List<String[]> mapHeaders, List<String[]> headers, List<Collection<T>> list)
            throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportXlsx(sheetNames, mapHeaders, headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsxInputStreamByBean(String[] headers, String[] mapHeaders,
                                                           List<T> list) throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportExcelX(headers, mapHeaders, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    public static <T> InputStream getXlsInputStreamByBean(String[] headers, List<T> list)
            throws IOException {
        File file = createTempFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ExcelUtil.exportXls(headers, list, out, null);
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            file.delete();
        }
    }

    private static File createTempFile() throws IOException {
        File temp = new File(
                PropertiesUtil.get("/config/properties/project", "temp.folder") + System
                        .currentTimeMillis());
        return temp;
    }

}
