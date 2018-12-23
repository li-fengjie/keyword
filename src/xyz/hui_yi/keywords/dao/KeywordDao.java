package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.KeywordBean;
import xyz.hui_yi.keywords.bean.KeywordPageBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

public class KeywordDao {
	
	public KeywordPageBean queryKeywordPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from keywords limit ?,?";
		KeywordPageBean keywordPageBean=new KeywordPageBean();
		try {
			List<KeywordBean> KeywordBeans=qr.query(sql, new BeanListHandler<KeywordBean>(KeywordBean.class),(pageno-1)*pagesize,pagesize);
			keywordPageBean.setPageNo(pageno);
			keywordPageBean.setPageSize(pagesize);
			keywordPageBean.setKeywordBeans(KeywordBeans);
			Object object=qr.query("select count(*) from keywords", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			keywordPageBean.setPageSum(num);
			return keywordPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public KeywordPageBean queryKeywordPageBean(int t_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from keywords where t_id=? order by k_id desc";
		KeywordPageBean keywordPageBean=new KeywordPageBean();
		try {
			List<KeywordBean> KeywordBeans=qr.query(sql, new BeanListHandler<KeywordBean>(KeywordBean.class),t_id);
			keywordPageBean.setKeywordBeans(KeywordBeans);
			Object object=qr.query("select count(*) from keywords where t_id=?", new ScalarHandler(),t_id);
			long num=(Long)object;
			keywordPageBean.setPageSum(num);
			return keywordPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertToKeywordBean(String k_id,String name,int t_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into keywords(k_id,name,t_id) values(?,?,?)";
		try {
			qr.update(sql,k_id,name,t_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteKeywordBean(int k_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from keywords where k_id=?";
		try {
			qr.update(sql,k_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public KeywordBean selectKeywordBean(int k_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from keywords where k_id=?";
		try {
			KeywordBean KeywordBeans = qr.query(sql, new BeanHandler<KeywordBean>(KeywordBean.class),k_id);
			return KeywordBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
