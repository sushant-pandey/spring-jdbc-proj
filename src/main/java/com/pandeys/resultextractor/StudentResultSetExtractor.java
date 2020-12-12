package com.pandeys.resultextractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.pandeys.Student;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

	public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Student> listOfStudents = new ArrayList<Student>();
		while(rs.next()) {
			Student s = new Student(
					rs.getInt("Roll_No"),
					rs.getString("Name"),
					rs.getString("Address"));
			listOfStudents.add(s);
		}
		return listOfStudents;
	}

}
