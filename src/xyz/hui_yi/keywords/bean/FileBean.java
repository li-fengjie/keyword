package xyz.hui_yi.keywords.bean;

public class FileBean {
    private int d_id;
    private String filename;
    private String content;
    private int c_id;
    private String stockname;
    private String dir;

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
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

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "d_id='" + d_id + '\'' +
                ", filename='" + filename + '\'' +
                ", content='" + content + '\'' +
                ", c_id='" + c_id + '\'' +
                ", stockname='" + stockname + '\'' +
                ", dir='" + dir + '\'' +
                '}';
    }
}
