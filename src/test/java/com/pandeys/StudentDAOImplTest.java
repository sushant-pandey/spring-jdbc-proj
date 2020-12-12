package com.pandeys;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	
	@Test
	public void testFetchStudentByName() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		List<Student> listOfStudents = studentDAO.fetchStudentsByName("Name110");
		for(Student s : listOfStudents) {
			System.out.println(s);
		}
		context.close();
	}
	
	@Test
	public void testFetchStudentGroupedByAddress() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		Map<String, List<String>> groupStudentsByAddress = studentDAO.groupStudentsByAddress();
		System.out.println(groupStudentsByAddress);
		
		//Print values of hashmap using entry set
		for( Entry<String, List<String>> entrySet : groupStudentsByAddress.entrySet()) {
			System.out.println(entrySet.getKey() + "::::" + entrySet.getValue());
		}
		context.close();
	}
	
	
	@Test
	public void testUpdateSingleStudentAddress() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		
		Student name106 = new Student();
		name106.setAddress("Address106");
		name106.setRollNum(106);
		studentDAO.update(name106);
		
		context.close();
	}
	
	@Test
	public void testUpdateMultipleStudentsAddress() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDAO = context.getBean("studentDAO", StudentDAOImpl.class);
		
		Student name107 = new Student();
		name107.setAddress("Address107");
		name107.setRollNum(107);
		
		Student name108 = new Student();
		name108.setAddress("Address108");
		name108.setRollNum(108);
		
		Student name109 = new Student();
		name109.setAddress("Address109");
		name109.setRollNum(109);
		
		List<Student> listOfStudents = new ArrayList<Student>(Arrays.asList(name107, name108, name109));
		int[] updatedRecords = studentDAO.updateStudents(listOfStudents);
		System.out.println("Updated Records = " + Arrays.toString(updatedRecords));
		context.close();
	}
}
