package com.tibame.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tibame.domain.MySQLReference;
@WebServlet("/ConnectionDemo")
public class ConnectDemo extends HttpServlet {
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 設定支援編碼
		response.setCharacterEncoding("UTF-8");
		// 1.取出MySQL Reference Bean
		MySQLReference ref = context.getBean("mysql", MySQLReference.class);

		try {
			Class.forName(ref.getDriverClassName());
			// DriverManager
			Connection connection = DriverManager.getConnection(ref.getUrl(), ref.getUserName(), ref.getPassWord());
			if (!connection.isClosed()) {
				response.getWriter().println("資料庫連接成功!");
				connection.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().println("資料庫連接失敗:"+e.getMessage());
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
