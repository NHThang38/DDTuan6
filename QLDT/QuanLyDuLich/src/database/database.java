package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
	public static Connection Con() {
		try {
			String url = "jdbc:sqlserver://localhost:1433;databasename=qldl";
			String user = "sa";
			String password = "sapassword";
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("connection fail"+e);
		}
		return null;
	}
}
