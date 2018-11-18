package xyz.hui_yi.keywords.servlet;


import org.apache.pdfbox.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件下载
 * 两个头一个流
 */
@WebServlet("/downloadFile")
public class DownloadFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileDir = "D:\\文件\\图片\\121.jpg";
        /*
        防止文件名乱码
         */
        String frameName = new String("121.jpg".getBytes("GBK"),"iso-8859-1");

        String contentType = this.getServletContext().getMimeType(fileDir);
        String contentDisposition = "attachment;filename="+frameName;

        FileInputStream inputStream = new FileInputStream(fileDir);

        response.setHeader("ContentType",contentType);
        response.setHeader("Content-Disposition",contentDisposition);

        //绑定响应端的流
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
    }
}
