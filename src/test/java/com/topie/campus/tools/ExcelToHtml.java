package com.topie.campus.tools;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by chenguojun on 9/19/16.
 */
public class ExcelToHtml {

    public static void main(String[] args) {
        ExcelToHtml excelToHtml = new ExcelToHtml();
        try {
            excelToHtml.convertExcel2Html("/tmp/test.xls", "/tmp/test.html");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void convertExcel2Html(String excelFilePath, String htmlFilePath)
            throws IOException, ParserConfigurationException, TransformerException {
        File excelFile = new File(excelFilePath);
        File htmlFile = new File(htmlFilePath);
        File htmlFileParent = new File(htmlFile.getParent());
        InputStream is = null;
        OutputStream out = null;
        StringWriter writer = null;
        try {
            if (excelFile.exists()) {
                if (!htmlFileParent.exists()) {
                    htmlFileParent.mkdirs();
                }
                is = new FileInputStream(excelFile);
                HSSFWorkbook workBook = new HSSFWorkbook(is);
                ExcelToHtmlConverter converter = new ExcelToHtmlConverter(
                        DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

                converter.processWorkbook(workBook);

                writer = new StringWriter();
                Transformer serializer = TransformerFactory.newInstance().newTransformer();
                serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                serializer.setOutputProperty(OutputKeys.INDENT, "yes");
                serializer.setOutputProperty(OutputKeys.METHOD, "html");
                serializer.transform(new DOMSource(converter.getDocument()), new StreamResult(writer));
                out = new FileOutputStream(htmlFile);
                System.out.println(writer.getBuffer().toString());
                out.write(writer.toString().getBytes("UTF-8"));
                out.flush();
                out.close();
                writer.close();
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (out != null) {
                    out.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
