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
 * Servlet implementation class ReservationServlet
 */
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		ReservationDao rd = new ReservationDao();
		List<Reservations> res = rd.getMyReservations(email);
		
		String reservationTable = "<table><thead><tr><th>Location</th><th>Address</th><th>Room Type</th><th>Check In</th><th>Check Out</th><th>Price</th><th>Status</th>";
		reservationTable += "</tr></thead><tbody><tr><td>";
		if (res.get(0).getHotel_id() == 1) {
			reservationTable += "Midtown Location</td><td>1888 Madison</td><td>";
			if (res.get(0).getRoom_type().equalsIgnoreCase("single")) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_type().equalsIgnoreCase("double")) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
			
		} else if (res.get(0).getHotel_id() == 2) {
			reservationTable += "East Memphis Location</td><td>6284 Poplar</td><td>";
			if (res.get(0).getRoom_type().equalsIgnoreCase("single")) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_type().equalsIgnoreCase("double")) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
		}else if (res.get(0).getHotel_id() == 3) {
			reservationTable += "Downtown Location</td><td>212 Danny Thomas</td><td>";
			if (res.get(0).getRoom_type().equalsIgnoreCase("single")) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_type().equalsIgnoreCase("double")) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
		}
		reservationTable += res.get(0).getCheck_in_date() + "</td><td>" + res.get(0).getCheck_out_date() + "</td><td>";
		reservationTable += res.get(0).getTotal_price() + "</td><td>" + res.get(0).getStatus() + "</td></tr></tbody></table>";
		reservationTable += "<br><br><div class=\"box has-text-centered\">\r\n" + 
				"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" + 
				"				  	<p id=\"butClicked\"></p>\r\n" + 
				"				</div>";
		
		PrintWriter pw = response.getWriter();
		pw.println(reservationTable);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String whichLoc = request.getParameter("whichLoc");
		String whatRoom = request.getParameter("whatRoom");
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		
		// try this line in the reservationDao to convert date type to sql date type
		// preparedStatement.setDate(index, new java.sql.Date(date.getTime()));
		ReservationDao rd = new ReservationDao();
		
		if (rd.makeReservation(email, whichLoc, whatRoom, checkIn, checkOut)) {
			String s = "<p>Reservation made. Status pending</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s);
		} else {
			String resConfirmation = "<h4>Failed to make reservation, please try again later</h4>";
			resConfirmation += "<br><br><div class=\"box has-text-centered\">\r\n" + 
					"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" + 
					"				  	<p id=\"butClicked\"></p>\r\n" + 
					"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(resConfirmation);
		}
		
		
		
		
	}

}
