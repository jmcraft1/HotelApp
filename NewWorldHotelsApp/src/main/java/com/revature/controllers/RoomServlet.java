package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.RoomDao;
import com.revature.model.Room;

/**
 * Servlet implementation class RoomServlet
 */
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String whichLocation = request.getParameter("whichLoc");
		
		RoomDao rd = new RoomDao();
		int countSingle = 0, countDouble = 0, countSuite = 0;
		List<Room> rooms = rd.getAllAvailableRooms(whichLocation);
		String tableString = "<table><thead><tr><th>Location</th><th>Address</th><th>Number of rooms</th><th>Room Type</th>";
		tableString += "<th>Price/Night</th><th>Maximum Occupancy</th></tr></thead><tbody><tr><td>";
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).getRoom_type().equalsIgnoreCase("single")) {
				countSingle++;
			} else if (rooms.get(i).getRoom_type().equalsIgnoreCase("double")) {
				countDouble++;
			} else if (rooms.get(i).getRoom_type().equalsIgnoreCase("suite")) {
				countSuite++;
			}
		}
		
		if (rooms.get(0).getHotel_id() == 1) {
			tableString += "Midtown Location</td><td>1888 Madison</td><td>" + countSingle + "</td><td>Single</td><td>$100</td><td>2</td></tr>";
			tableString += "<tr><td>Midtown Location</td><td>1888 Madison</td><td>" + countDouble + "</td><td>Double</td><td>$150</td><td>4</td></tr>";
			tableString += "<tr><td>Midtown Location</td><td>1888 Madison</td><td>" + countSuite + "</td><td>Suite</td><td>$300</td><td>10</td></tr>";
		} else if (rooms.get(0).getHotel_id() == 2) {
			tableString += "<tr><td>East Memphis Location</td><td>6284 Poplar</td><td>" + countSingle + "</td><td>Single</td><td>$100</td><td>2</td></tr>";
			tableString += "<tr><td>East Memphis Location</td><td>6284 Poplar</td><td>" + countDouble + "</td><td>Double</td><td>$150</td><td>4</td></tr>";
			tableString += "<tr><td>East Memphis Location</td><td>6284 Poplar</td><td>" + countSuite + "</td><td>Suite</td><td>$300</td><td>10</td></tr>";
		} else if (rooms.get(0).getHotel_id() == 3) {
			tableString += "<tr><td>Downtown Location</td><td>212 Danny Thomas</td><td>" + countSingle + "</td><td>Single</td><td>$100</td><td>2</td></tr>";
			tableString += "<tr><td>Downtown Location</td><td>212 Danny Thomas</td><td>" + countDouble + "</td><td>Double</td><td>$150</td><td>4</td></tr>";
			tableString += "<tr><td>Downtown Location</td><td>212 Danny Thomas</td><td>" + countSuite + "</td><td>Suite</td><td>$300</td><td>10</td></tr>";
		}
		tableString += "</tbody></table><br><br><div class=\"box has-text-centered\">\r\n" + 
				"				  	<a href=\"HomePage.html\" class=\"button is-primary\">Back</a> \r\n" + 
				"				  	<p id=\"butClicked\"></p>\r\n" + 
				"				</div>";
		PrintWriter pw = response.getWriter();
		pw.println(tableString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String whichLocation = request.getParameter("whichLocation");
		String whatRoomType = request.getParameter("whatRoomType");
		RoomDao rd = new RoomDao();
		
		List<Room> rooms = rd.getAllRooms(whichLocation, whatRoomType);
		String tableString = "<table><thead><tr><th>Room Id</th><th>Room Number</th>";
		tableString += "<th>Price/Night</th><th>Maximum Occupancy</th><th>Occupied</th></tr></thead><tbody>";
		for (int i = 0; i < rooms.size(); i++) {
			tableString += "<tr><td>" + rooms.get(i).getRoom_id() + "</td><td>" + rooms.get(i).getRoom_num() + "</td><td>" + rooms.get(i).getPrice_per_night();
			tableString += "</td><td>" + rooms.get(i).getMax_occupancy() + "</td><td>" + rooms.get(i).getOccupied() + "</td></tr>";
		}
		
		tableString += "</tbody></table><br><br><div class=\"box has-text-centered\">\r\n" + 
				"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" + 
				"				  	<p id=\"butClicked\"></p>\r\n" + 
				"				</div>";
		PrintWriter pw = response.getWriter();
		pw.println(tableString);
	}

}
