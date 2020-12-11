package com.pandeys;

import java.util.ArrayList;
import java.util.List;

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

		Object[] objArray = { student.getRollNum(), student.getName(), student.getAddress() };
		int numberOfRowsInserted = jdbcTemplate.update(insertStudentQuery, objArray);
		System.out.println("Num of rows inserted " + numberOfRowsInserted);
	}

	public boolean deleteRecordByRollNo(int rollNum) {
		String deleteStudentByRollNum = "delete from Student where Roll_No = ?";
		int countOfRecordsDeleted = jdbcTemplate.update(deleteStudentByRollNum, rollNum);
		return countOfRecordsDeleted == 1;
	}

	public int deleteRecordsByNameAndLocation(String name, String location) {
		String deleteStudentByNameLocation = "delete from Student where Name = ? or Address = ?";
		int countOfRecordsDeleted = jdbcTemplate.update(deleteStudentByNameLocation, name, location);
		return countOfRecordsDeleted;
	}
	
	
	public void cleanUp() {
		String truncateStudentTable = "truncate table Student";
		jdbcTemplate.execute(truncateStudentTable);
	}

	public void insert(List<Student> students) {
		String batchAddStudentsSql = "insert into Student values (?, ?, ?)";
		List<Object[]> listOfStudentDetails = new ArrayList<Object[]>();
		for(Student s : students) {
			Object[] objArr = {s.getRollNum(), s.getName(), s.getAddress()};
			listOfStudentDetails.add(objArr);
		}
		
		jdbcTemplate.batchUpdate(batchAddStudentsSql, listOfStudentDetails);
	}

}