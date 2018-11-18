package xyz.hui_yi.keywords.utils.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailUtils {
    /**
     * 发送邮件 使用 163 邮箱发送无附件
     * @param from 使用的邮箱（需要为163邮箱）
     * @param password 邮箱密码
     * @param to    收件人
     * @param Cc    抄送
     * @param Bcc   密送
     * @param title 邮件标题
     * @param content 邮件内容
     * @return 是否成功
     */
    public static boolean sendEmailBy163(String from,String password,String to,String Cc,String Bcc,String title,String content) {
        int index = from.lastIndexOf("@");
        String username = from.substring(0,index);
        System.out.println(username);
        /*
        1、得到session
         */
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.163.com");
        properties.setProperty("mail.smtp.auth","true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };

        Session session = Session.getInstance(properties,authenticator);
        /*
        2.创建mimeMessage
         */
        try {
            MimeMessage message = new MimeMessage(session);

            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");

            message.setFrom(new InternetAddress(from));//发件人
            message.setRecipients(Message.RecipientType.TO,to);//收件人
            message.setRecipients(Message.RecipientType.CC,Cc);//抄送
            message.setRecipients(Message.RecipientType.BCC,Bcc);//密送
            message.setSubject(title);
            message.setContent(content,"text/html;charset=utf-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    /**
     * 发送邮件 使用 163 邮箱发送含附件
     * @param from
     * @param password
     * @param to
     * @param Cc
     * @param Bcc
     * @param title
     * @param content
     * @param file  附件
     * @param fileName 附件名
     * @return
     */
    public static boolean sendEmailBy163(String from,String password,String to,String Cc,String Bcc,String title,String content,File file,String fileName) {
        int index = from.lastIndexOf("@");
        String username = from.substring(0,index);
        System.out.println(username);
        /*
        1、得到session
         */
        Properties properties = new Properties();
        properties.setProperty("mail.host","smtp.163.com");
        properties.setProperty("mail.smtp.auth","true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };

        Session session = Session.getInstance(properties,authenticator);
        /*
        2.创建mimeMessage
         */
        try {
            MimeMessage message = new MimeMessage(session);

            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");

            message.setFrom(new InternetAddress(from));//发件人
            message.setRecipients(Message.RecipientType.TO,to);//收件人
            message.setRecipients(Message.RecipientType.CC,Cc);//抄送
            message.setRecipients(Message.RecipientType.BCC,Bcc);//密送
            message.setSubject(title);

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content,"text/html;charset=utf-8");
            multipart.addBodyPart(mimeBodyPart);
            MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
            mimeBodyPart1.attachFile(file);
            mimeBodyPart1.setFileName(MimeUtility.decodeText(fileName));
            multipart.addBodyPart(mimeBodyPart1);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
