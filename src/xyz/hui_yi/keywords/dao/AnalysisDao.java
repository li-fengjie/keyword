package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.AnalysisBean;
import xyz.hui_yi.keywords.bean.AnalysisPageBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

public class AnalysisDao {
	
	public AnalysisPageBean queryAnalysisPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from result limit ?,?";
		AnalysisPageBean AnalysisPageBean=new AnalysisPageBean();
		try {
			List<AnalysisBean> AnalysisBeans=qr.query(sql, new BeanListHandler<AnalysisBean>(AnalysisBean.class),(pageno-1)*pagesize,pagesize);
			AnalysisPageBean.setPageNo(pageno);
			AnalysisPageBean.setPageSize(pagesize);
			AnalysisPageBean.setAnalysisBeans(AnalysisBeans);
			Object object=qr.query("select count(*) from result", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			AnalysisPageBean.setPageSum(num);
			return AnalysisPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AnalysisPageBean queryAnalysisPageBean() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from result order by r_id desc";
		AnalysisPageBean AnalysisPageBean=new AnalysisPageBean();
		try {
			List<AnalysisBean> AnalysisBeans=qr.query(sql, new BeanListHandler<AnalysisBean>(AnalysisBean.class));
			AnalysisPageBean.setAnalysisBeans(AnalysisBeans);
			Object object=qr.query("select count(*) from result", new ScalarHandler());
			long num=(Long)object;
			AnalysisPageBean.setPageSum(num);
			return AnalysisPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertToAnalysisBean(String starttime,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into result(r_id,starttime,state) values(?,?,?)";
		try {
			qr.update(sql,null,starttime,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAnalysisBean(int r_id,String endtime,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update result set state=?,endtime=? where r_id=?";
		try {
			qr.update(sql,state,endtime,r_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void startAnalysisBean(int r_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update result set state=? where r_id=?";
		try {
			qr.update(sql,0,r_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void stopAnalysisBean(int r_id) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update result set state=? where r_id=?";
        try {
            qr.update(sql,1,r_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void deleteAnalysisBean(int r_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from result where r_id=?";
		try {
			qr.update(sql,r_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int selectAnalysisBean(String starttime) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from result where starttime=?";
		try {
			AnalysisBean AnalysisBean = qr.query(sql, new BeanHandler<AnalysisBean>(AnalysisBean.class),starttime);
			return AnalysisBean.getR_id();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
