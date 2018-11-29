package test;

import xyz.hui_yi.keywords.utils.mail.Mail;
import xyz.hui_yi.keywords.utils.mail.MailUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.File;
import java.io.IOException;

public class MailDemo {
    public static void main(String[] args) throws IOException, MessagingException {
        File file = new File("D:\\QQDownload\\1052125651\\FileRecv\\MobileFile\\2017年申万行业分类.xlsx");
        Session session = MailUtils.createSession("smtp.163.com","chuangkejiajia@163.com","lifengjie0323");
//        Mail mail = new Mail("chuangkejiajia@163.com","1052125651@qq.com","来自码上中国博客激活邮件","" +
//                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>您的激活码为：1234\\n 点击即可激活：<a href=\\\"http://127.0.0.1:8080/EmailServlet?method=active&code={1}\\");
        Mail mail = new Mail("chuangkejiajia@163.com","1052125651@qq.com","来自码上中国博客激活邮件","" +
                //文件名乱码解决
                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></head><body>您的激活码为：1234\\n 点击即可激活：<a href=\\\"http://127.0.0.1:8080/EmailServlet?method=active&code={1}\\",file,"2017年申万行业分类.xlsx");

//        Mail mail = new Mail("chuangkejiajia@163.com","1052125651@qq.com","账号激活:\\n","码上中国博客激活邮件",file,"2017年申万行业分类");
        MailUtils.send(session,mail);
    }
}
