package com.cyz.utils;

import java.util.PropertyResourceBundle;

public class TestInitialize {
	
	public static void main(String[] args) {
		PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle("sql_execute_properties");
		String drivername = configBundle.getString("DRIVERNAME");
		System.out.println(drivername);
	}
}
