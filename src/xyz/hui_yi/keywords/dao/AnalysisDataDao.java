package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.AnalysisBean;
import xyz.hui_yi.keywords.bean.AnalysisDataBean;
import xyz.hui_yi.keywords.bean.AnalysisPageBean;
import xyz.hui_yi.keywords.bean.FileBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

/**
 * result_data 分析数据表
 */
public class AnalysisDataDao {

	public void insertToAnalysisDataBean(int r_id,int d_id,int k_id,int c_id,int count) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into result_data(r_id,d_id,k_id,c_id,count) values(?,?,?,?,?)";
		try {
			qr.update(sql,r_id,d_id,k_id,c_id,count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int selectMaxD_id(int r_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select MAX(d_id) from result_data where r_id=?";
		try {
			AnalysisDataBean analysisDataBean= qr.query(sql, new BeanHandler<AnalysisDataBean>(AnalysisDataBean.class),r_id);
			return analysisDataBean.getD_id();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<AnalysisDataBean> selectAnalysisDataBean(int r_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from result_data where r_id=?";
		try {
			List<AnalysisDataBean> analysisDataBeans= qr.query(sql, new BeanListHandler<AnalysisDataBean>(AnalysisDataBean.class),r_id);
			return analysisDataBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
