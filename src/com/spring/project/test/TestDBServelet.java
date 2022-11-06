package com.spring.project.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDBServelet
 */
@WebServlet("/TestDBServelet")
public class TestDBServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//setup connection to variables
		String user = "springstudent";
		String pass = "springstudent";
		
		String jdbc = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		//connect to database
		try {
			PrintWriter out = response.getWriter();
			
			//out.println will print the response in web page rather than in console
			out.println("Connecting to database " + jdbc);
			
			Class.forName(driver);
			
			Connection myCon = DriverManager.getConnection(jdbc, user, pass);
			
			out.println("Connection Successful");
			
			myCon.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
