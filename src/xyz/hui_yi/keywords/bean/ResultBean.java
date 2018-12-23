package xyz.hui_yi.keywords.bean;

/**
 * 最终数据
 */
public class ResultBean {
    private String industry;
    private String stockname;//股票名称
    private String stockcode;
//    private String name;//指标
    private String name;//指标名称
    private int count;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "industry='" + industry + '\'' +
                ", stockname='" + stockname + '\'' +
                ", stockcode='" + stockcode + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
