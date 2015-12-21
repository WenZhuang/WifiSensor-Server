package com.bupt.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;



public class DBUtil {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/ardb";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	private static Connection conn = null;
	
	static {		
		try {
			//1.加载数据库
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得数据库的连接
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static Connection getConnection(){
		return conn;
	}

	
 }
