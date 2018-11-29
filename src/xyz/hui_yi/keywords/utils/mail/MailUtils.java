package xyz.hui_yi.keywords.utils.mail;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

/**
 * 邮件工具类
 */
public class MailUtils {
    /**
     * 获取 session
     * @param host 邮箱主机名
     * @param uname 邮箱用户名（不包括 @后内容）
     * @param password 邮箱密码
     * @return session
     */
    public static Session createSession(String host, String uname, String password){
        Properties properties = new Properties();
        properties.setProperty("mail.host",host);//设置主机
        properties.setProperty("mail.smtp.auth","true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(uname,password);
            }
        };
        return Session.getInstance(properties,authenticator);
    }

    /**
     * 发送邮件
     * @param session
     * @param mail 邮件对象
     */
    public static void send(Session session, Mail mail) throws MessagingException, IOException {
        MimeMessage message = new MimeMessage(session);
        /*
        伪装头
         */
        message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
        message.setFrom(new InternetAddress(mail.getFrom()));//发件人
        message.setRecipients(Message.RecipientType.TO,mail.getTo());//收件人
        message.setSubject(mail.getSubject());
        System.out.println(mail.toString());
        if (mail.getFile() != null && mail.getFile().exists()) {
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mail.getContent(),"text/html;charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);
            MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
            mimeBodyPart1.attachFile(mail.getFile());
            mimeBodyPart1.setFileName(MimeUtility.decodeText(mail.getFileName()));
            multipart.addBodyPart(mimeBodyPart1);
            message.setContent(multipart);
        }else {
            message.setContent(mail.getContent(),"text/html;charset=utf-8");
        }
        Transport.send(message);
    }
}
