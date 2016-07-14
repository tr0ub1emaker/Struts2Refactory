package com.cyz.db2dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

/*
 * This is a test Connect method.
 * 
 * */
public class DBConnection {

	private static Connection connection;

	// String sql = "select * from Cyz.T_USER";
	// Connection ct = null;
	// PreparedStatement ps = null;
	// ResultSet rs = null;

	public static void initialize() {

		PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle("sql_execute_properties");
		String driverName = configBundle.getString("DRIVERNAME");
		String url = configBundle.getString("DB_URL");
		String user = configBundle.getString("DB_USERNAME");
		String password = configBundle.getString("DB_PASSWORD");
		
		System.out.println("starting connect DB initialize ");
		try {
			
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end connect DB initialize");
	}

	/**
	 * Return singleton connection.
	 * @return
	 */
	public static Connection getConnection(){
		return connection;
	}
	
	public void closeConnection() throws Exception{
		if (connection != null & !connection.isClosed()) {
			connection.close();
		}else {
			throw new Exception("connection is not exist. ");
		}
		System.out.println("Connection is closed. ");
	}
	
	
	public static void main(String[] args) {
		DBConnection.initialize();
		
	}
}
