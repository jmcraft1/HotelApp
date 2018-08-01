package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		
		String reservationTable = "<table><thead><tr><th>Location</th><th>Address</th><th>Room Type</th><th>Check In</th><th>Check Out</th><th>Price></th>";
		reservationTable += "</tr></thead><tbody><tr><td>";
		if (res.get(0).getRoom_id() > 0 && res.get(0).getRoom_id() < 101) {
			reservationTable += "Midtown Location</td><td>1888 Madison</td><td>";
			if (res.get(0).getRoom_id() < 41) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_id() < 91) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
			
		} else if (res.get(0).getRoom_id() > 100 && res.get(0).getRoom_id() < 201) {
			reservationTable += "East Memphis Location</td><td>6284 Poplar</td><td>";
			if (res.get(0).getRoom_id() < 141) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_id() < 191) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
		}else if (res.get(0).getRoom_id() > 200 && res.get(0).getRoom_id() < 301) {
			reservationTable += "Downtown Location</td><td>212 Danny Thomas</td><td>";
			if (res.get(0).getRoom_id() < 241) {
				reservationTable += "Single</td><td>";
			} else if (res.get(0).getRoom_id() < 291) {
				reservationTable += "Double</td><td>";
			} else {
				reservationTable += "Suite</td><td>";
			}
		}
		reservationTable += res.get(0).getCheck_in_date() + "</td><td>" + res.get(0).getCheck_out_date() + "</td><td>";
		reservationTable += res.get(0).getTotal_price() + "</td></tr></tbody></table>";
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
		Date checkInDate = null;
		Date checkOutDate = null;
		try {
			checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkIn);
			checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkOut);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// try this line in the reservationDao to convert date type to sql date type
		// preparedStatement.setDate(index, new java.sql.Date(date.getTime()));
		ReservationDao rd = new ReservationDao();
		boolean success = rd.makeReservation(email, whichLoc, whatRoom, checkInDate, checkOutDate);
		
	}

}
