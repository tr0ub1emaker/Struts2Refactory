package com.cyz.dbdao.impl;

import com.cyz.dbdao.DAO;
import com.cyz.dbdao.DBConnection;

public class UserDaoImpl {

	public boolean MatchPassword(String uname, String password){
		
		String pw = null;
		
		DBConnection.initialize();
		DAO dao = new DAO();
		pw = dao.selectOneByOne("SELECT U_PW FROM Cyz.UUSER WHERE U_NAME='"+ uname + "'");
		
		return pw.equals(password);
	}

}
