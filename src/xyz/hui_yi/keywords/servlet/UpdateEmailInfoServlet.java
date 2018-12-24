package xyz.hui_yi.keywords.servlet;

import xyz.hui_yi.keywords.dao.EmailDao;
import xyz.hui_yi.keywords.utils.commons.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedReader;

@WebServlet("/updateEmailInfo")
public class UpdateEmailInfoServlet extends HttpServlet {
    EmailDao emailDao = new EmailDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String host = request.getParameter("host");
        String to = request.getParameter("to");
        String from = request.getParameter("from");
        String password = request.getParameter("password");
        if(host != null && to != null && from != null && password != null &&
             host != "" && to != "" && from != "" && password != ""
                ){
//            String strPath = this.getClass().getClassLoader().getResource("").getPath();
//            System.out.println(strPath);
//            String path = strPath + "/email_template.properties";
//            PropertiesUtil.updatePro(path,"host",host);
//            PropertiesUtil.updatePro(path,"to",to);
//            PropertiesUtil.updatePro(path,"from",from);
//            PropertiesUtil.updatePro(path,"password",password);
            emailDao.updateEmailBeanHost(host);
            emailDao.updateEmailBeanFrom(from);
            emailDao.updateEmailBeanPwd(password);
            emailDao.updateEmailBeanTo(to);
            response.getWriter().println("success");
        }else {
            response.getWriter().println("输入信息不得为空");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
