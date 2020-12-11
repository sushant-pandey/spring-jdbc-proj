package com.pandeys.step01;

import org.springframework.jdbc.core.JdbcTemplate;

import com.pandeys.datasource.JdbcConnection;

public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void insert(Student student) {
		jdbcTemplate = JdbcConnection.getJDBCTemplate();
		
		String insertStudentQuery = "INSERT INTO Student VALUES (?, ?, ?)";
		
		Object[] objArray = {student.getRollNum(), student.getName(), student.getAddress()};
		jdbcTemplate.update(insertStudentQuery, objArray);
		}
		
	}