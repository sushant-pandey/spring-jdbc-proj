package com.pandeys;

import java.util.List;
import java.util.Map;

public interface StudentDAO {
	
	void insert(Student student);
	
	void insert(List<Student> student);
	
	boolean deleteRecordByRollNo(int rollNum);
	
	int deleteRecordsByNameAndLocation(String name, String location);
	
	List<Student> fetchAllStudents();
	
	Student fetchStudentByRollNo(int rollNum);
	
	List<Student> fetchStudentsByName(String name);
	
	Map<String, List<String>> groupStudentsByAddress();
	
	int update(Student student);
	
	int[] updateStudents(List<Student> students);
}
