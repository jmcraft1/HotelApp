package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.CustomerDao;
import com.revature.dao.EmployeeDao;

/**
 * Servlet implementation class SignInServlet
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
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
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		CustomerDao cd = new CustomerDao();
		EmployeeDao ed = new EmployeeDao();
		//String retEmail = "<h3>Logged in as " + email + "</h3>";
		if(cd.verifyCred(email, pass)) {
			RequestDispatcher view = request.getRequestDispatcher("HomePage.html");
			//request.setAttribute("email", retEmail);
			view.forward(request, response);
		} /*else if (ed.verifyManager(email, pass)) {
		 	RequestDispatcher view = request.getRequestDispatcher("ManagerHomePage.html");
		 	view.forward(request, response);
		} 
		*/
		else if (ed.verifyCred(email, pass)){
			RequestDispatcher view = request.getRequestDispatcher("EmployeeHomePage.html");
			//request.setAttribute("email", retEmail);
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("index.html");
			view.forward(request, response);
		}
	}

}
