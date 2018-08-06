package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.CustomerDao;
import com.revature.dao.ReservationDao;

/**
 * Servlet implementation class StatusServlet
 */
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String curEmail = request.getParameter("curEmail");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		CustomerDao cd = new CustomerDao();
		if (cd.updateCustomer(fName, lName, address, city, state, zip, phone, email, curEmail)) {
			String s = "<p>Your profile has been updated</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cust_id = request.getParameter("cust_id");
		String aOrD = request.getParameter("aOrD");
		
		ReservationDao rd = new ReservationDao();
		if(rd.updateStatus(cust_id, aOrD)) {
			String s = "<p>Status updated</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s); 
		}
	}

}
