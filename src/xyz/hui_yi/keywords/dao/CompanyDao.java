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

	public CompanyPageBean queryCompanyPageBean() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from company order by c_id desc";
		CompanyPageBean CompanyPageBean=new CompanyPageBean();
		try {
			List<CompanyBean> CompanyBeans=qr.query(sql, new BeanListHandler<CompanyBean>(CompanyBean.class));
			CompanyPageBean.setCompanyBeans(CompanyBeans);
			Object object=qr.query("select count(*) from company", new ScalarHandler());
			long num=(Long)object;
			CompanyPageBean.setPageSum(num);
			return CompanyPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertToCompanyBean(String c_id,String cname,String industry,String stockcode,String stockname,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into company(c_id,cname,industry,stockcode,stockname,state) values(?,?,?,?,?,?)";
		try {
			qr.update(sql,c_id,cname,industry,stockcode,stockname,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//(c_id,cname,industry,stockcode,stockname,state)
	public void updateCompanyBean(String c_id,String cname,String industry,String stockcode,String stockname,int state) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update company set cname=?,industry=?,stockcode=?,stockname=?,state=? where c_id=?";
		try {
			qr.update(sql,cname,industry,stockcode,stockname,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//(c_id,stockcode)
	public void updateCompanyBean(String c_id,String stockcode) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update company set stockcode=? where c_id=?";
		try {
			qr.update(sql,stockcode,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void startCompanyBean(int c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update company set state=? where c_id=?";
		try {
			qr.update(sql,0,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void stopCompanyBean(int c_id) {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update company set state=? where c_id=?";
        try {
            qr.update(sql,1,c_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void deleteCompanyBean(int c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from company where c_id=?";
		try {
			qr.update(sql,c_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public CompanyBean selectCompanyBean(int c_id) {
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
