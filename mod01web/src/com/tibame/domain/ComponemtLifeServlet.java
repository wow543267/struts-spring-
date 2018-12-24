package com.tibame.domain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet("/ComponemtLifeServlet")
public class ComponemtLifeServlet extends HttpServlet {

	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final long serialVersionUID = 1L;
	int Servletid;
    //建構子
	public ComponemtLifeServlet() {

		Servletid = (int) (Math.random() * 10000)+1;
		System.out.println(Servletid);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		//取出Dao bean
		IDao<CustomersBean> dao = context.getBean("MyDao", IDao.class);
        PrintWriter out=response.getWriter();
	    out.println("Object id:"+((CustomersDao2) dao).number());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
