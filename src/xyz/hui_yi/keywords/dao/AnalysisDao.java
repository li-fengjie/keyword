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

	public void insertToAnalysisBean(String c_id,String cname,String industry,String stockcode,String stockname,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into result(c_id,cname,industry,stockcode,stockname,state) values(?,?,?,?,?,?)";
		try {
			qr.update(sql,c_id,cname,industry,stockcode,stockname,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//(c_id,cname,industry,stockcode,stockname,state)
	public void updateAnalysisBean(String c_id,String cname,String industry,String stockcode,String stockname,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update result set cname=?,industry=?,stockcode=?,stockname=?,state=? where c_id=?";
		try {
			qr.update(sql,cname,industry,stockcode,stockname,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//(c_id,stockcode)
	public void updateAnalysisBean(String c_id,String stockcode) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update result set stockcode=? where c_id=?";
		try {
			qr.update(sql,stockcode,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void startAnalysisBean(int c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update result set state=? where c_id=?";
		try {
			qr.update(sql,0,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void stopAnalysisBean(int c_id) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update result set state=? where c_id=?";
        try {
            qr.update(sql,1,c_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void deleteAnalysisBean(int c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from result where c_id=?";
		try {
			qr.update(sql,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public AnalysisBean selectAnalysisBean(String c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from result where c_id=?";
		try {
			AnalysisBean AnalysisBeans = qr.query(sql, new BeanHandler<AnalysisBean>(AnalysisBean.class),c_id);
			return AnalysisBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
