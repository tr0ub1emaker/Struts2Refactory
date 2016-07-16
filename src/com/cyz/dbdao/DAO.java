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
	
	public DAO() {
		this.initialize();
	}

	private void initialize(){
		System.out.println("start geting DB connection.");
		this.connection = DBConnection.getConnection();
		System.out.println("get DB connection success. ");
	}
	
	public String selectOneByOne(String sql){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultCount = 0;
		String returnString = "";
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			System.out.println(sql);
			
			while (rs.next()) {
				ResultSetMetaData resultData = rs.getMetaData();
				resultCount = resultData.getColumnCount();
				
				if (resultCount == 1) {
					returnString = rs.getString(1);
				}else if (resultCount > 1) {
					
					System.out.println("the column has one more results, need investigation!!");
					returnString = rs.getString(1);
				}else {
					return "";
				}
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnString;
	}
	
	@SuppressWarnings("rawtypes")
	public <T> LinkedList executeSelect(Class<T> elementClass, String sql) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<BeanBase> lin = new LinkedList<BeanBase>();
		try {
			
			System.out.println("execute: " + sql);
			
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				BeanBase element = (BeanBase) elementClass.newInstance();
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int count = rsmd.getColumnCount();
				
				System.out.println("result count: " + count);
				
				if (count == 0) {
					System.out.println("no result found!");
				}
				
				for (int i = 1; i < count; i++) {
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
