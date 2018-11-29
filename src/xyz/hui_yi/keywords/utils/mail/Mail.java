package xyz.hui_yi.keywords.utils.mail;

import java.io.File;

public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    private File file;
    private String fileName;
    /**
     * 创建无附件邮件对象
     * @param from 发件人
     * @param to 收件人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public Mail(String from,String to,String subject,String content){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    /**
     * 创建有附件的邮件对象
     * @param from 发件人
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param file 附件
     */
    public Mail(String from,String to,String subject,String content,File file,String fileName){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.file = file;
        this.fileName = fileName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", file=" + file +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
