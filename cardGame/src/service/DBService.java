package service;

import java.sql.Connection;
import java.sql.DriverManager;

class DBService {
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/puzzle", "root", "java1234");
		//Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/wewe1418", "wewe1418", "tjqj4580!");
		return conn;
	}
}
