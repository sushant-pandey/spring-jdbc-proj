package com.pandeys;

public interface StudentDAO {
	
	void insert(Student student);
	
	boolean deleteRecordByRollNo(int rollNum);
}
