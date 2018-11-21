package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.CompanyPageBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

public class CompanyDao {
	
	public CompanyPageBean queryCompanyPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from company limit ?,?";
		CompanyPageBean CompanyPageBean=new CompanyPageBean();
		try {
			List<CompanyBean> CompanyBeans=qr.query(sql, new BeanListHandler<CompanyBean>(CompanyBean.class),(pageno-1)*pagesize,pagesize);
			CompanyPageBean.setPageNo(pageno);
			CompanyPageBean.setPageSize(pagesize);
			CompanyPageBean.setCompanyBeans(CompanyBeans);
			Object object=qr.query("select count(*) from company", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			CompanyPageBean.setPageSum(num);
			return CompanyPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertToCompanyBean(String c_id,String cname,String industry,String stockcode,String stockname,String state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into company(c_id,cname,industry,stockcode,stockname,state) values(?,?,?,?,?,?)";
		try {
			qr.update(sql,c_id,cname,industry,stockcode,stockname,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//(c_id,cname,industry,stockcode,stockname,state)
	public void updateCompanyBean(String c_id,String cname,String industry,String stockcode,String stockname,String state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update company set cname=?,industry=?,stockcode=?,stockname=?,state=? where c_id=?";
		try {
			qr.update(sql,cname,industry,stockcode,stockname,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCompanyBean(String c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from company where c_id=?";
		try {
			qr.update(sql,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public CompanyBean selectCompanyBean(String c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from company where c_id=?";
		try {
			CompanyBean CompanyBeans = qr.query(sql, new BeanHandler<CompanyBean>(CompanyBean.class),c_id);
			return CompanyBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
