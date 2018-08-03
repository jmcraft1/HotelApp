package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ReservationDao;
import com.revature.model.Reservations;

/**
 * Servlet implementation class CheckInServlet
 */
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReservationDao rd = new ReservationDao();
		List<Reservations> list = rd.getPendingReservations();
		String s = "<table><thead><tr><th>Customer Email</th><th>Reservation Id</th><th>Hotel Id</th><th>Check In Date</th><th>Check Out Date</th><th>Room Type</th></tr></thead><tbody>";
		for (int i = 0; i < list.size(); i++) {
			s += "<tr><td>" + list.get(i).getCust_id() + "</td><td>" + list.get(i).getReservation_id() + "</td><td>" + list.get(i).getHotel_id() + "</td><td>" + list.get(i).getCheck_in_date();
			s += "</td><td>" + list.get(i).getCheck_out_date() + "</td><td>" + list.get(i).getRoom_type() + "</td></tr>";
		}
		s += "</tbody></table>";
		s += "<div class=\"box has-text-centered\">\r\n" +
				"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
				"				  	<p id=\"butClicked\"></p>\r\n" +  
				"				</div>";
		PrintWriter pw = response.getWriter();
		pw.println(s);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String whichLocation = request.getParameter("whichLocation");
		String roomNum = request.getParameter("room_num");
		String email = request.getParameter("email");
		
		ReservationDao rd = new ReservationDao();
		boolean success = rd.checkInGuest(email, roomNum, whichLocation);
		if (success) {
			String s = "<p>Guest checked in</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s);
		}
	}

}
