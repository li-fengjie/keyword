package xyz.hui_yi.keywords.bean;

public class KeywordBean {
    private int k_id;
    private String name;
    private int t_id;

    public int getK_id() {
        return k_id;
    }

    public void setK_id(int k_id) {
        this.k_id = k_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    @Override
    public String toString() {
        return "KeywordBean{" +
                "k_id=" + k_id +
                ", name='" + name + '\'' +
                ", t_id=" + t_id +
                '}';
    }
}
