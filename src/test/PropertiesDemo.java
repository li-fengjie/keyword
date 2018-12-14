package test;


import xyz.hui_yi.keywords.utils.commons.PropertiesUtil;

import java.util.ResourceBundle;

public class PropertiesDemo {
    public static void main(String[] args) {
        ResourceBundle bundle=ResourceBundle.getBundle("dbconfig");
        String driverClass=bundle.getString("driverClass");
//        String url=bundle.getString("url");
//        String user=bundle.getString("user");
//        String password=bundle.getString("password");
//        System.out.println(driverClass);
//        System.out.println(password);
        PropertiesUtil.updatePro("src/email_template.properties","subject","测试");
    }
}
