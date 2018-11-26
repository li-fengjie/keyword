package xyz.hui_yi.keywords.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import xyz.hui_yi.keywords.bean.CompanyBean;
import xyz.hui_yi.keywords.bean.CompanyPageBean;
import xyz.hui_yi.keywords.bean.FileBean;
import xyz.hui_yi.keywords.bean.FilePageBean;
import xyz.hui_yi.keywords.utils.db.C3P0Utils;

import java.sql.SQLException;
import java.util.List;

public class FileDao {
	
	public FilePageBean queryFilePageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from file_data limit ?,?";
		FilePageBean FilePageBean=new FilePageBean();
		try {
			List<FileBean> FileBeans=qr.query(sql, new BeanListHandler<FileBean>(FileBean.class),(pageno-1)*pagesize,pagesize);
			FilePageBean.setPageNo(pageno);
			FilePageBean.setPageSize(pagesize);
			FilePageBean.setFileBeans(FileBeans);
			Object object=qr.query("select count(*) from file_data", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			FilePageBean.setPageSum(num);
			return FilePageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public FilePageBean queryFilePageBean() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from file_data  order by d_id desc";
		FilePageBean FilePageBean=new FilePageBean();
		try {
			List<FileBean> FileBeans=qr.query(sql, new BeanListHandler<FileBean>(FileBean.class));
			FilePageBean.setFileBeans(FileBeans);
			Object object=qr.query("select count(*) from file_data", new ScalarHandler());
			long num=(Long)object;
			FilePageBean.setPageSum(num);
			return FilePageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertToFileBean(String d_id,String filename,String content,String c_id,String dir) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into file_data(d_id,filename,content,c_id,dir) values(?,?,?,?,?)";
		try {
			qr.update(sql,d_id,filename,content,c_id,dir);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//(d_id,filename,content,c_id,dir)
	public void updateFileBean(String d_id,String filename,String content,String c_id,String dir) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update file_data set filename=?,content=?,c_id=?,dir=? where d_id=?";
		try {
			qr.update(sql,filename,content,c_id,dir,d_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	更改文件所属公司
	 */
	public void updateFileBean(String d_id,String c_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update file_data set c_id=? where d_id=?";
		try {
			qr.update(sql,c_id,d_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFileBean(int d_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from file_data where d_id=?";
		try {
			qr.update(sql,d_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public FileBean selectFileBean(int d_id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from file_data where d_id=?";
		try {
			FileBean FileBeans = qr.query(sql, new BeanHandler<FileBean>(FileBean.class),d_id);
			return FileBeans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
