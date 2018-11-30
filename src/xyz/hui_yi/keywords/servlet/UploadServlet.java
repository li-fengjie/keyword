package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.CompanyPageBean;
import xyz.hui_yi.keywords.bean.FileBean;
import xyz.hui_yi.keywords.bean.FilePageBean;
import xyz.hui_yi.keywords.dao.CompanyDao;
import xyz.hui_yi.keywords.dao.FileDao;
import xyz.hui_yi.keywords.utils.commons.CommonsUtils;
import xyz.hui_yi.keywords.utils.file.FileType;
import xyz.hui_yi.keywords.utils.findwords.CountWord;
import xyz.hui_yi.keywords.utils.pdf.Pdf2String;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传 Server 3.0 支持
 *
 */

@WebServlet(name = "UploadServlet",urlPatterns = "/upload")
@MultipartConfig //支持文件上传的注解
public class UploadServlet extends HttpServlet {
    private FileDao fileDao = new FileDao();
    private CompanyDao companyDao = new CompanyDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*
        获取文件表单字段，对应的Part 对象
         */
        Part part = request.getPart("file");
        if(FileType.validateFileTypeByMimeType(part.getContentType())){
            String root = this.getServletContext().getRealPath("/WEB-INF/pdfFiles");
            /*
                处理文件名是绝对路径问题
             */
            String saveName =part.getSubmittedFileName();
            int index = saveName.lastIndexOf("\\");
            if(index != -1){
                saveName = saveName.substring(index+1);
            }
            String filename = saveName;
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

            //pdf 转 文本
            String content = Pdf2String.pdf2String(dir);
            System.out.println(content);
            //检测文件所属公司
            String c_id = null;
            if(content != null){
                CompanyPageBean companyPageBean = companyDao.queryCompanyPageBean();
                List<CompanyBean> companyBeans = companyPageBean.getCompanyBeans();
                for (CompanyBean companyBean:companyBeans) {
                    //如果包含公司的名字/股票代码/股票名称 就认为是该公司
                    if(companyBean.getName() != null && CountWord.isContainWord(saveName,companyBean.getName())
                    || CountWord.isContainWord(content,companyBean.getName())){
                        c_id = companyBean.getC_id();
                        System.out.println(c_id + companyBean.getName());
                        break;
                    }
//                    else if(companyBean.getStockcode() != null && CountWord.isContainWord(content,companyBean.getStockcode())){
//                        c_id = companyBean.getC_id();
//                        break;
//                    }
                    else if(companyBean.getStockname() != null && CountWord.isContainWord(content,companyBean.getStockname())){
                        c_id = companyBean.getC_id();
                        System.out.println(c_id + companyBean.getStockname());
                        break;
                    }
                }
            }
            //数据库存储文件位置
            fileDao.insertToFileBean(null,filename,content,c_id,dir);
        }else {
            response.getWriter().println("File type error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
