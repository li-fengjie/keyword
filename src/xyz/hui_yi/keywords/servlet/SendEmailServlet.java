package xyz.hui_yi.keywords.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xyz.hui_yi.keywords.bean.AnalysisBean;
import xyz.hui_yi.keywords.bean.EmailBean;
import xyz.hui_yi.keywords.bean.ResultBean;
import xyz.hui_yi.keywords.dao.AimsResultDao;
import xyz.hui_yi.keywords.dao.AnalysisDao;
import xyz.hui_yi.keywords.dao.EmailDao;
import xyz.hui_yi.keywords.utils.commons.PropertiesUtil;
import xyz.hui_yi.keywords.utils.mail.Mail;
import xyz.hui_yi.keywords.utils.mail.MailUtils;
import xyz.hui_yi.keywords.utils.poi.JSON2ExcelUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sendEmail")
public class SendEmailServlet extends HttpServlet {
    AnalysisDao analysisDao = new AnalysisDao();
    private AimsResultDao aimsResultDao = new AimsResultDao();
    private List<ResultBean> resultBeans;
    EmailDao emailDao = new EmailDao();

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
        EmailBean emailBean = emailDao.selectEmailBean();
        String host = emailBean.getHost();
        String to = emailBean.getToo();
        String from = emailBean.getFromm();
        String password = emailBean.getPassword();
        String r_id = request.getParameter("r_id");
        AnalysisBean analysisBean = analysisDao.selectAnalysisBean(Integer.parseInt(r_id));
        if(password != null && to != null && from!= null && host != null){
            String starttime = analysisBean.getStarttime();
            starttime = starttime.replaceAll(":",".");
            String root = this.getServletContext().getRealPath("/WEB-INF/excelFiles");
            String fileDir = root + "/" + starttime + ".xls";
            File file = new File(fileDir);
            if(!file.exists()){
                resultBeans = new ArrayList<>();
                resultBeans = aimsResultDao.selectAimDataBean(Integer.parseInt(r_id));
                JSONArray jsonArray = JSONArray.fromObject(resultBeans);
                JSON2ExcelUtils.JSON2Excel(jsonArray,fileDir);
            }
            Session session = MailUtils.createSession(host,from,password);
//            Session session = MailUtils.createSession("smtp.163.com","chuangkejiajia@163.com","lifengjie0323");

//        Mail mail = new Mail("chuangkejiajia@163.com","1052125651@qq.com","来自码上中国博客激活邮件","" +
//                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>您的激活码为：1234\\n 点击即可激活：<a href=\\\"http://127.0.0.1:8080/EmailServlet?method=active&code={1}\\");
            Mail mail = new Mail(from,to,"来自关键词分析统计系统的分析结果邮件","" +
                    //文件名乱码解决
                    "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>欢迎使用关键词分析统计系统，结果请在附件中查看 您的激活码为：1234",file,"关键词分析统计结果" + ".xls");

//        Mail mail = new Mail("chuangkejiajia@163.com","1052125651@qq.com","账号激活:\\n","码上中国博客激活邮件",file,"2017年申万行业分类");
            try {
                MailUtils.send(session,mail);
            } catch (MessagingException e) {
                response.getWriter().println("发送失败！" + e.toString());
                e.printStackTrace();
                return;
            }
            response.getWriter().println("发送成功");
        }else {
            response.getWriter().println("请在系统设置中补全邮箱信息");
        }
    }
}
