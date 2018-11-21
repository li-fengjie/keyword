package xyz.hui_yi.keywords.utils.db;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtils {
	private static String url;
	private static String driverClass;
	private static String user;
	private static String password;
	static {
		ResourceBundle bundle=ResourceBundle.getBundle("dbconfig");
		driverClass=bundle.getString("driverClass");
		url=bundle.getString("url");
		user=bundle.getString("user");
		password=bundle.getString("password");
		
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	public static void closeAll(ResultSet rs,Statement st,Connection conn) throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		if(st!=null) {
			st.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	
//	public Admin getAdmin(String sql, String Adminname, String password) {
//		Admin Admin=null;
//		try {
//			Connection conn=getConnection();
//			PreparedStatement ps=conn.prepareStatement(sql);
//			ps.setString(1, Adminname);
//			ps.setString(2, password);
//			ResultSet rs=ps.executeQuery();
//			Class<Admin> cls=Admin.class;
//			Field [] fields= cls.getDeclaredFields();
//			for (int i = 0; i < fields.length; i++) {
//				fields[i].setAccessible(true);
//
//			}
//			if(rs!=null) {
//
//				for (;rs.next();) {
//					Admin=new Admin();
//					for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//						Object object=rs.getObject(fields[i].getName());
//						fields[i].set(Admin, object);
//					}
//
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return Admin;
//	}
//
//	public Admin getAdmin(String sql) {
//		Admin Admin=null;
//		try {
//			Connection conn=getConnection();
//			PreparedStatement ps=conn.prepareStatement(sql);
//			ResultSet rs=ps.executeQuery();
//			Class<Admin> cls=Admin.class;
//			Field [] fields= cls.getDeclaredFields();
//			for (int i = 0; i < fields.length; i++) {
//				fields[i].setAccessible(true);
//
//			}
//			if(rs!=null) {
//				Admin=new Admin();
//				for (;rs.next();) {
//					for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//						Object object=rs.getObject(fields[i].getName());
//						fields[i].set(Admin, object);
//					}
//
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return Admin;
//	}
}
