package com.pandeys;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentDAOImplTest {

	@Test
	public void testInsertionOfRecord() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDaoImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		studentDaoImpl.insert(new Student(104, "Student Name 04", "Location Address 04"));
		context.close();
	}

	@Test
	public void testDeletionOfRecordByRollNum() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDaoImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		boolean recordDeleted = studentDaoImpl.deleteRecordByRollNo(102);
		assertTrue(recordDeleted);
		context.close();
	}
}
