package com.pandeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("studentDAO")
public class StudentDAOImpl implements StudentDAO {

	@Autowired
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