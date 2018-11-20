package xyz.hui_yi.keywords.bean;

public class CompanyBean {
    private String c_id;
    private String name;
    private String industry;
    private String stockcode;
    private String stockname;
    private String state;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CompanyBean{" +
                "c_id='" + c_id + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", stockcode='" + stockcode + '\'' +
                ", stockname='" + stockname + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
