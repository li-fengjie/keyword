package xyz.hui_yi.keywords.bean;

public class EmailBean {
    private String host;
    private String fromm;
    private String too;
    private String password = "";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFromm() {
        return fromm;
    }

    public void setFromm(String fromm) {
        this.fromm = fromm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToo() {
        return too;
    }

    public void setToo(String too) {
        this.too = too;
    }

    @Override
    public String toString() {
        return "EmailBean{" +
                "host='" + host + '\'' +
                ", fromm='" + fromm + '\'' +
                ", too='" + too + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
