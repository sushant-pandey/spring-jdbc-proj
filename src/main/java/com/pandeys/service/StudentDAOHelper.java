package com.pandeys.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandeys.Student;
import com.pandeys.StudentDAO;

@Service("studentDaoHelper")
public class StudentDAOHelper {
	
	@Autowired
	private StudentDAO studentDao;
	
	public void setUpStudentTable() {
		List<Student> listOfStudents = Arrays.asList(
				new Student(101, "Name101", "Address101"),
				new Student(102, "Name102", "Address102"),
				new Student(103, "Name103", "Address103"),
				new Student(104, "Name104", "Address104"),
				new Student(105, "Name105", "Address105"),
				new Student(106, "Name106", "Address101"),
				new Student(107, "Name107", "Address102"),
				new Student(108, "Name108", "Address103"),
				new Student(109, "Name109", "Address104"),
				new Student(110, "Name110", "Address105")
				);
		studentDao.insert(listOfStudents);
	}
}
