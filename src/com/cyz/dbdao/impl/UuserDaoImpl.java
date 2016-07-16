package com.cyz.dbdao.impl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import com.cyz.bean.BeanBase;
import com.cyz.bean.Uuser;
import com.cyz.dbdao.DAO;
import com.cyz.dbdao.DBConnection;

public class UuserDaoImpl {

	/**
	 * get the password by username
	 * **/
	@SuppressWarnings("unchecked")
	public String getPassword(String uname){
		
		DBConnection.initialize();
		DAO dao = new DAO();
		return dao.selectOneByOne("SELECT U_PW FROM Cyz.UUSER WHERE U_NAME='"+ uname + "'");
		
	}
	
	public static void main(String[] args) {
		UuserDaoImpl udi = new UuserDaoImpl();
		String pass = udi.getPassword("frank");
		System.out.println(pass);
	}
}
