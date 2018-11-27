package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.TypeTargetPageBean;
import xyz.hui_yi.keywords.bean.TypeTargetBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

public class TypeTargetDao {
	
	public TypeTargetPageBean queryTypeTargetPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from type_target limit ?,?";
		TypeTargetPageBean typeTargetPageBean=new TypeTargetPageBean();
		try {
			List<TypeTargetBean> typeTargetBeans=qr.query(sql, new BeanListHandler<TypeTargetBean>(TypeTargetBean.class),(pageno-1)*pagesize,pagesize);
			typeTargetPageBean.setPageNo(pageno);
			typeTargetPageBean.setPageSize(pagesize);
			typeTargetPageBean.setTypeTargetBeans(typeTargetBeans);
			Object object=qr.query("select count(*) from type_target", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			typeTargetPageBean.setPageSum(num);
			return typeTargetPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public TypeTargetPageBean queryTypeTargetPageBean() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from type_target order by t_id";
		TypeTargetPageBean typeTargetPageBean=new TypeTargetPageBean();
		try {
			List<TypeTargetBean> typeTargetBeans=qr.query(sql, new BeanListHandler<TypeTargetBean>(TypeTargetBean.class));
			typeTargetPageBean.setTypeTargetBeans(typeTargetBeans);
			Object object=qr.query("select count(*) from type_target", new ScalarHandler());
			long num=(Long)object;
			typeTargetPageBean.setPageSum(num);
			return typeTargetPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertToTypeTargetBean(String t_id,String name) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into type_target(t_id,name) values(?,?)";
		try {
			qr.update(sql,t_id,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	(t_id,name)
	public void updateTypeTargetBean(String t_id,String name) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update type_target set name=? where t_id=?";
		try {
			qr.update(sql,t_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTypeTargetBean(int t_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from type_target where t_id=?";
		try {
			qr.update(sql,t_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TypeTargetBean selectTypeTargetBean(String t_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from type_target where t_id=?";
		try {
			TypeTargetBean TypeTargetBeans = qr.query(sql, new BeanHandler<TypeTargetBean>(TypeTargetBean.class),t_id);
			return TypeTargetBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
