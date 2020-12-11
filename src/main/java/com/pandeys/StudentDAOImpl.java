package com.pandeys;

import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDAOImpl implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(Student student) {
		String insertStudentQuery = "INSERT INTO Student VALUES (?, ?, ?)";
		
		Object[] objArray = {student.getRollNum(), student.getName(), student.getAddress()};
		int numberOfRowsInserted = jdbcTemplate.update(insertStudentQuery, objArray);
		System.out.println("Num of rows inserted " + numberOfRowsInserted);
		}
		
	}