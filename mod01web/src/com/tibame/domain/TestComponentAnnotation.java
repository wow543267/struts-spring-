package com.tibame.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestComponentAnnotation {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IDao<CustomersBean> dao = context.getBean("dao3", IDao.class);
//		System.out.println(dao);

	}

}
