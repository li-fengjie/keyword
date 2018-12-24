package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import xyz.hui_yi.keywords.bean.EmailBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;

public class EmailDao {

	public void insertToEmailBean(String host,String from,String to,String password) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into email(id,host,fromm,too,password) values(1,?,?,?,?)";
		try {
			qr.update(sql,host,from,to,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmailBeanHost(String host) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update email set host=? where id=1";
        try {
			qr.update(sql,host);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void updateEmailBeanFrom(String from) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql1 = "update email set fromm=? where id=1";
        try {
            qr.update(sql1,from);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmailBeanTo(String to) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql2 = "update email set too=? where id=1";
        try {
            qr.update(sql2,to);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEmailBeanPwd(String password) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql3 = "update email set password=? where id=1";
        try {
            qr.update(sql3,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public EmailBean selectEmailBean() {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from email where id=1";
        try {
            EmailBean emailBeans = qr.query(sql, new BeanHandler<EmailBean>(EmailBean.class));
            return emailBeans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
