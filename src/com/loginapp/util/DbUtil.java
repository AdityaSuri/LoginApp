package com.loginapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

	public static Connection getConnection() {
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String user="hr";
			String pass="hr";
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			con=DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
}
