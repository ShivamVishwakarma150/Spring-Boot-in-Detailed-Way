package com.app.shivam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection con = null;

	static {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/boot", "root", "Shivam@123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}
}
