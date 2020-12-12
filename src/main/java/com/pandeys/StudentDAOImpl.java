package com.pandeys;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pandeys.mapper.StudentRowMapper;
import com.pandeys.resultextractor.StudentAddressGroupResultSetExtractor;
import com.pandeys.resultextractor.StudentResultSetExtractor;

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

	public List<Student> fetchAllStudents() {
		String fetchAllStudentsSql = "select Roll_No as rollNum, "
				+ "Name as name, "
				+ "Address as address "
				+ "from Student";
		List<Student> listOfStudents = jdbcTemplate.query(fetchAllStudentsSql, 
				new BeanPropertyRowMapper<Student>(Student.class));
		return listOfStudents;
	}

	public Student fetchStudentByRollNo(int rollNum) {
		String fetchStudentSql = "select Roll_No as rollNum, "
				+ "Name as name, "
				+ "Address as address "
				+ "from Student where Roll_No = ? ";
		Student student = jdbcTemplate.queryForObject(fetchStudentSql,
				 new BeanPropertyRowMapper<Student>(Student.class), 
				 rollNum);
		return student;
}

	public List<Student> fetchStudentsByName(String name) {
		String fetchStudentsByNameSql = "select * from Student where Name = ?";
		return jdbcTemplate.query(fetchStudentsByNameSql, new StudentResultSetExtractor(), name);
	}

	public Map<String, List<String>> groupStudentsByAddress() {
		String fetchAllStudentsSql = "select * from Student";
		return jdbcTemplate.query(fetchAllStudentsSql, new StudentAddressGroupResultSetExtractor());
	}

	public int update(Student student) {
		String updateStudentSql = "update Student set Address = ? where Roll_No = ? ";
		return jdbcTemplate.update(updateStudentSql, student.getAddress(), student.getRollNum());
	}

	public int[] updateStudents(final List<Student> students) {
		String updateStudentsSql = "update Student set Address = ? where Roll_No = ? ";
		int[] updatedRecords = jdbcTemplate.batchUpdate(updateStudentsSql, new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, students.get(i).getAddress());
				ps.setInt(2, students.get(i).getRollNum());
			}

			public int getBatchSize() {
				return students.size();
			}
		});
		return updatedRecords;
	}

}