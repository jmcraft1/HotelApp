package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.CustomerDao;
import com.revature.dao.RoomDao;
import com.revature.model.Customer;
import com.revature.model.Room;

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
		String location = request.getParameter("whichLocation");
		RoomDao rd = new RoomDao();
		List<Room> rooms = rd.getAllGuests(location); 
		//System.out.println(rooms.get(0).toString());
		String guestList = "<table><thead><tr><th>Guest Email</th><th>Room Type</th><th>Room Number</th></tr></thead><tbody>";
		for (int i = 0; i < rooms.size(); i++) {
			guestList += "<tr><td>" + rooms.get(i).getCust_email() + "</td><td>" + rooms.get(i).getRoom_type() + "</td><td>" + rooms.get(i).getRoom_num() + "</td></tr>";
		}
		guestList += "</tbody></table>";
		guestList += "<div class=\"box has-text-centered\">\r\n" + 
								"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
								"				  	<p id=\"butClicked\"></p>\r\n" +  
								"				</div>";
		PrintWriter pw = response.getWriter();
		pw.println(guestList);
		
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
