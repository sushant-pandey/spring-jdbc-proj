package com.pandeys;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pandeys.service.StudentDAOHelper;

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
	
	@Test
	public void testDeletionOfRecordByNameAndAddress() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDaoImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		int recordsDeleted = studentDaoImpl.deleteRecordsByNameAndLocation("NameXXX", "AddressXXX");
		System.out.println("records deleted = " + recordsDeleted);
		assertTrue(recordsDeleted > 0);
		context.close();
	}
	
	@Test
	public void testTruncationOfStudentTable() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDaoImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		studentDaoImpl.cleanUp();
		context.close();
	}
	
	
	@Test
	public void testBatchInsertOfStudentTable() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOHelper studentDAOHelper = context.getBean("studentDaoHelper", StudentDAOHelper.class);
		studentDAOHelper.setUpStudentTable();
		context.close();
	}
	
	@Test
	public void testFetchAllStudents() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		List<Student> listOfStudents = studentDAO.fetchAllStudents();
		for(Student s : listOfStudents) {
			System.out.println(s);
		}
		context.close();
	} 
	
	@Test
	public void testFetchStudentByRollNum() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		Student student = studentDAO.fetchStudentByRollNo(108);
		System.out.println(student);
		context.close();
	} 
}
