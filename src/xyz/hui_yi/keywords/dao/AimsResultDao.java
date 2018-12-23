package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.hui_yi.keywords.bean.AnalysisBean;
import xyz.hui_yi.keywords.bean.AnalysisDataBean;
import xyz.hui_yi.keywords.bean.ResultBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

/**
 * result_data 分析数据表
 */
public class AimsResultDao {
	public List<ResultBean> selectAimDataBean(int r_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select industry,stockcode,stockname,name,SUM(count) as count from result_aims where r_id=? group by stockname,name";
		try {
			List<ResultBean> resultBeans = qr.query(sql, new BeanListHandler<ResultBean>(ResultBean.class),r_id);
			return resultBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
