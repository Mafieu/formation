package com.EPF.CLI.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {
	INSTANCE;
	
	private ConnectionManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Sql connection failed");
		}
	}
	
	public Connection getConnection() throws Exception {
		try {
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/computer-database-db?user=root&password=root&zeroDateTimeBehavior=convertToNull");
		} catch (SQLException e) {
			throw new Exception("Error while getting connection");
		}
	}
	
	public void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to close connection");
		}
	}
}
