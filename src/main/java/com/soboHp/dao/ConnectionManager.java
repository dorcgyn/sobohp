package com.soboHp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static Connection connect = null;
	
	private static void createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?user=root&password=12345678");
	}
	
	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (connect == null) {
			createConnection();
		}
		return connect;
	}
}
