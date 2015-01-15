package net.fantesy84.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	/**
	 * 连接数据库所需要的必要信息
	 */
	private static final String DRIVER_NAME = PropertyUtils.getInstance().getValue(SystemContents.JDBC_DRIVER);
	private static final String CONN_URL = PropertyUtils.getInstance().getValue(SystemContents.JDBC_URL);
	private static final String USER = PropertyUtils.getInstance().getValue(SystemContents.JDBC_USER);
	private static final String PWD = PropertyUtils.getInstance().getValue(SystemContents.JDBC_PWD);
	
	private static Connection conn;
	
	static {
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(CONN_URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
