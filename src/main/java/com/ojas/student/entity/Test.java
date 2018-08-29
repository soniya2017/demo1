package com.ojas.student.entity;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
	
	/*private BasicDataSource basicDataSource;
	

	public BasicDataSource getBasicDataSource() {
		return basicDataSource;
	}

	public void setBasicDataSource(BasicDataSource basicDataSource) {
		this.basicDataSource = basicDataSource;
	}

	void m1() {
		Connection con = null;
		try {
			con = basicDataSource.getConnection();
			if (null != con) {
				System.out.println("Connected");
			} else {
				System.out.println("failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Resource r = new ClassPathResource("context.xml");
		BeanFactory factory = new XmlBeanFactory(r);
		Test t = (Test)factory.getBean("testId");
		System.out.println(t);
		
		
	    AbstractApplicationContext context= new ClassPathXmlApplicationContext("context.xml");

	    Test t = (Test) context.getBean("testId");

		System.out.println(t);

	    context.close();
	ApplicationContext context= new ClassPathXmlApplicationContext("context.xml");
		Test t = (Test) context.getBean("testId");
		 ((AbstractApplicationContext) context).registerShutdownHook();
		System.out.println(t);
	}*/
	}

