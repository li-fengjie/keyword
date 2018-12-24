package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.EmailBean;
import xyz.hui_yi.keywords.dao.EmailDao;
import xyz.hui_yi.keywords.utils.commons.PropertiesUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/emailInfo")
public class EmailServlet extends HttpServlet {
    private EmailDao emailDao = new EmailDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=utf-8");
//        String strPath = this.getClass().getClassLoader().getResource("").getPath();
//        System.out.println(strPath);
//        String path = strPath + "/email_template.properties";
//        String host = PropertiesUtil.getProperty(path,"host");
//        String to = PropertiesUtil.getProperty(path,"to");
//        String from = PropertiesUtil.getProperty(path,"from");
//        String password = PropertiesUtil.getProperty(path,"password");
//
//        EmailBean emailBean = new EmailBean();
//        emailBean.setFrom(from);
//        emailBean.setHost(host);
//        emailBean.setTo(to);
        EmailBean emailBean = emailDao.selectEmailBean();
        emailBean.setPassword("*********");
        if(emailBean.getPassword() != null && !emailBean.getPassword().equals("")){
            JSONObject jsonObject = JSONObject.fromObject(emailBean);
            System.out.println(jsonObject);
            response.getWriter().println(jsonObject.toString());
        }else {
            response.getWriter().println("输入密码有误,请检查邮箱信息");
        }
        System.out.println(emailBean.getPassword());
    }
}
