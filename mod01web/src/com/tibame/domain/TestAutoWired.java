package com.tibame.domain;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoWired {

	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IDao<CustomersBean> dao = context.getBean("dao2", IDao.class);
		// 取出注入的DataSource
		DataSource dataSource = dao.getDataSource();
		if (dataSource == null) {
			System.out.println("尚未自動注入");

		} else {

			System.out.println("自動注入");
            String dbName=dataSource.getConnection().getCatalog();
		    System.out.println(dbName);
		}

	}

}
