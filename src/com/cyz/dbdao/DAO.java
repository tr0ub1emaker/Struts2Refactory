package com.cyz.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.cyz.bean.BeanBase;

public class DAO {

	private Connection connection;
	private String sql;
	
	@SuppressWarnings("unused")
	private void initialize(String sqlContent){
		System.out.println("start geting DB connection.");
		this.connection = DBConnection.getConnection();
		this.sql = sqlContent;
		System.out.println("get DB connection success. ");
	}
	
	public <T> LinkedList<BeanBase> executeSelect(Class<T> elementClass) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BeanBase> lin = new LinkedList<BeanBase>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				BeanBase element = (BeanBase) elementClass.newInstance();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					element.setValue(rsmd.getColumnName(i), rs.getString(i).trim());
				}
				lin.add(element);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("sqlexception");
			}
		}
		return (LinkedList<BeanBase>) lin;
	}
}
