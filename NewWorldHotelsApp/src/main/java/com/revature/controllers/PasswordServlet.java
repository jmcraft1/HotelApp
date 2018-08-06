package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.CustomerDao;
import com.revature.model.Customer;

/**
 * Servlet implementation class PasswordServlet
 */
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		CustomerDao cd = new CustomerDao();
		List<Customer> list = cd.getCustomer(email);
		//System.out.println(list.get(0).getAddress());
		
		String s = "<p>Make any changes and click submit</p>";
		s += "<form method=\"get\" action=\"StatusServlet\">";
		s += "<p name=\"curEmail\" id=\"curEmail\">Current Email: " + list.get(0).getEmail() + "</p>";
		s += "<input type=\"text\" name=\"fName\" id=\"fName\" value=" + list.get(0).getfName() + "><br>";
		s += "<input type=\"text\" name=\"lName\" id=\"lName\" value=" + list.get(0).getlName() + "><br>";
		s += "<input type=\"text\" name=\"address\" id=\"address\" value=" + list.get(0).getAddress() + "><br>";
		s += "<input type=\"text\" name=\"city\" id=\"city\" value=" + list.get(0).getCity() + "><br>";
		s += "<input type=\"text\" name=\"state\" id=\"state\" value=" + list.get(0).getState() + "><br>";
		s += "<input type=\"text\" name=\"zip\" id=\"zip\" value=" + list.get(0).getZip() + "><br>";
		s += "<input type=\"text\" name=\"phone\" id=\"phone\" value=" + list.get(0).getPhone() + "><br>";
		s += "<input type=\"text\" name=\"email\" id=\"email\" value=" + list.get(0).getEmail() + "><br>";
		s += "<input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Submit\">";
		s += "</form>";
		PrintWriter pw = response.getWriter();
		pw.println(s);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String curPass = request.getParameter("curPass");
		String newPass = request.getParameter("newPass");
		CustomerDao cd = new CustomerDao();
		if (cd.updatePassword(email, curPass, newPass)) {
			String s = "<p>Password updated</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s);
		}
	}

}
