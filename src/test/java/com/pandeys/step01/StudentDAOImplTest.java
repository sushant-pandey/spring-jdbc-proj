package com.pandeys.step01;

import org.junit.Test;

public class StudentDAOImplTest {

	@Test
	public void test() {
		StudentDAOImpl studentDaoImpl = new StudentDAOImpl();
		studentDaoImpl.insert(new Student(101, "Student Name 01", "Location Address 01"));
	}

}
