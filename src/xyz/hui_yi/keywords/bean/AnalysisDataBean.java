package xyz.hui_yi.keywords.bean;

public class AnalysisDataBean {
    private int r_id;
    private int d_id;
    private int k_id;
    private int c_id;
    private int count;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public int getK_id() {
        return k_id;
    }

    public void setK_id(int k_id) {
        this.k_id = k_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AnalysisDataBean{" +
                "r_id='" + r_id + '\'' +
                ", d_id='" + d_id + '\'' +
                ", k_id='" + k_id + '\'' +
                ", c_id='" + c_id + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
