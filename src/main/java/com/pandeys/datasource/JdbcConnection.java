package com.pandeys.datasource;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcConnection {
	
	public static JdbcTemplate getJDBCTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	public static DataSource getDataSource() {
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/School";
		String userName = "root";
		String password = "password";
		
		return new DriverManagerDataSource(connectionUrl, userName, password);
	}
}
