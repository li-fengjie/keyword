package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.file.FileType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传 Server 3.0 支持
 *
 */

@WebServlet(name = "UploadServlet",urlPatterns = "/upload")
@MultipartConfig //支持文件上传的注解
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //可以使用getParameter
//        String username = request.getParameter("username");
//        System.out.println(username);
        /*
        获取文件表单字段，对应的Part 对象
         */
        Part part = request.getPart("file");
//        response.getWriter().println(part.getContentType());//image/png
//        response.getWriter().println(FileType.validateFileTypeByMimeType(part.getContentType()) + "");
//        response.getWriter().println(part.getName());//image
//        response.getWriter().println(part.getSize());//160902
//        response.getWriter().println(part.getHeader("Content-Disposition"));
        //form-data; name="image"; filename="0029cb500e5b4862b5ece04a74842dfc_th.png"
//        response.getWriter().println(part.getSubmittedFileName());//0029cb500e5b4862b5ece04a74842dfc_th.png
        if(FileType.validateFileTypeByMimeType(part.getContentType())){
            String root = this.getServletContext().getRealPath("/WEB-INF/pdfFiles");
            /*
                处理文件名是绝对路径问题
             */

            String saveName =part.getSubmittedFileName();
//            saveName = new String(saveName.getBytes("ISO-8859-1"),"utf-8");
            int index = saveName.lastIndexOf("\\");
            if(index != -1){
                saveName = saveName.substring(index+1);
            }
            saveName = CommonsUtils.getUUID() + "_" + saveName;
            /*
             * 打散文件存储（根据哈希值）
             */
            int hCode = saveName.hashCode();
            /*
             *转换成16进制
             */
            String hex = Integer.toHexString(hCode);
            /*
            生成完整路径
             */
            String dir = root +  "/" + hex.charAt(0) + "/" + hex.charAt(1) + "/" + saveName;
            File dirFile = new File(root,hex.charAt(0)+"/" + hex.charAt(1));
            //创建目录链
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            System.out.println(dir);
            //保存文件
            part.write(dir);
            //TODO 数据库存储文件位置
        }else {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
