package com.pandeys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pandeys.Student;

public class StudentRowMapper implements RowMapper<Student> {

	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Student(rs.getInt("Roll_No"), 
				rs.getString("Name"), 
				rs.getString("Address"));
	}

}
