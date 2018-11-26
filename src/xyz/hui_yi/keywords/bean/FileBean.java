package xyz.hui_yi.keywords.bean;

public class FileBean {
    private String d_id;
    private String filename;
    private String content;
    private String c_id;
    private String dir;

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "d_id='" + d_id + '\'' +
                ", filename='" + filename + '\'' +
                ", content='" + content + '\'' +
                ", c_id='" + c_id + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
