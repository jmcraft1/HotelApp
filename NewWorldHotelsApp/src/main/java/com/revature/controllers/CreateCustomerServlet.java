package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.CustomerDao;
import com.revature.model.Customer;

/**
 * Servlet implementation class CreateCustomerServlet
 */


public class CreateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		Customer c = new Customer(fName, lName, address, city, state, zip, phone, email, pass);
		
		CustomerDao cd = new CustomerDao();
		
		
		if (cd.createCustomer(c)) {
			RequestDispatcher view = request.getRequestDispatcher("HomePage.html");
			view.forward(request, response);
		} else {
			PrintWriter pw = response.getWriter();
			pw.println("<h2>Could not connect to database</h2>");
			pw.println("<h2>Please try again later</h2>");
		}
		
		
	}

}
