package xyz.hui_yi.keywords.utils.db;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {
	private static DataSource dataSource= new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return dataSource;
		
	}
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器忙...");
		}
		
	}
	
}
