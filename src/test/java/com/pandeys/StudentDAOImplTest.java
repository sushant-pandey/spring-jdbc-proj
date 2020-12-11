package com.pandeys;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentDAOImplTest {

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-02.xml");
		System.out.println("Context loaded");
		StudentDAOImpl studentDaoImpl = context.getBean("studentDAO", StudentDAOImpl.class);
		studentDaoImpl.insert(new Student(103, "Student Name 03", "Location Address 03"));
		context.close();
	}

}
