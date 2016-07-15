package com.cyz.dbdao;

import java.util.HashMap;

public class DAOFactory {
	
	/** The singleton DAO factory map  */
	private static final HashMap<String, DAO> daoFactory = new HashMap<String, DAO>();

	public static DAO getDAO (String sqlName) {
		if (daoFactory.containsKey(sqlName)) {
			return daoFactory.get(sqlName);
		}
		else {
			DAO dao = new DAO();
			daoFactory.put(sqlName, dao);
			return dao;
		}
	}
}
