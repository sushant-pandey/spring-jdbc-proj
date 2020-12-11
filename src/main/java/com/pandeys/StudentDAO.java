package com.pandeys;

import java.util.List;

public interface StudentDAO {
	
	void insert(Student student);
	
	void insert(List<Student> student);
	
	boolean deleteRecordByRollNo(int rollNum);
	
	int deleteRecordsByNameAndLocation(String name, String location);
}
