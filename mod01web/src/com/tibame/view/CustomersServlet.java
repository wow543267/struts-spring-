package com.tibame.view;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tibame.domain.CustomerDao;
import com.tibame.domain.CustomersBean;
import com.tibame.domain.IDao;
import com.tibame.domain.MySQLReference;

@WebServlet("/CustomersServlet")
public class CustomersServlet extends HttpServlet {
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		MySQLReference ref = context.getBean("mysql", MySQLReference.class);

//		// datasource
//		BasicDataSource datasource = new BasicDataSource();
//		// 設定屬性
//		datasource.setDriverClassName(ref.getDriverClassName());
//		datasource.setUrl(ref.getUrl());
//		datasource.setUsername(ref.getUserName());
//		datasource.setPassword(ref.getPassWord());
//		// dao
//		IDao<CustomersBean> dao = new CustomerDao();
//		// 屬性注入
//		dao.setdataSource(datasource);

		
		//只用窗口物件進行操作
		IDao<CustomersBean> dao = context.getBean("dao",IDao.class);
		
		String sql = "select * from saki where customerid=?";

		try {

			CustomersBean bean = dao.queryForObject(sql,1);//
			response.getWriter().println(bean.getFirstName()+bean.getLastName());

		} catch (SQLException e) {
			response.getWriter().println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
