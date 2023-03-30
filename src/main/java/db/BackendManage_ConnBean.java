package db;

import java.sql.*;
/** 
* @author PC
*/
public class BackendManage_ConnBean {
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:";
	private String database = "D:/h2db2/hospital";
	private String userName = "sa";
	private String password = "";
	private Connection connection = null;

	public Connection getConnection() {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url + database, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection != null)
			return connection;
		else {
			System.out.println("connection is not correct");
			return null;
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
			connection = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
