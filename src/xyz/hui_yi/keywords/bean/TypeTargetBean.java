package xyz.hui_yi.keywords.bean;

public class TypeTargetBean {
    private int t_id;
    private String name;

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeTargetBean{" +
                "t_id=" + t_id +
                ", name=" + name +
                '}';
    }
}
