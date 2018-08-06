package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.MessengerDao;
import com.revature.model.Messages;

/**
 * Servlet implementation class HostMessageServlet
 */
public class HostMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HostMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MessengerDao md = new MessengerDao();
		List<Messages> list = new ArrayList<>();
		list = md.getHostMessages();
		if (list.size() > 0) {
			String retSt = "<table><thead><tr><th>From</th><th>To</th><th>Message</th></tr></thead><tbody>";
			for (int i = 0; i < list.size(); i++) {
				retSt += "<tr><td>" + list.get(i).getMessageFrom() + "</td><td>" + list.get(i).getMessageTo() + "</td><td>" + list.get(i).getMessage() + "</td></tr>";
			}
			retSt += "</tbody></table><br><br><div class=\"box has-text-centered\">\r\n" + 
					"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" + 
					"				  	<p id=\"butClicked\"></p>\r\n" + 
					"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(retSt);
		}
		else {
			PrintWriter pw = response.getWriter();
			pw.println("<p>No messages</p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String messToCust = request.getParameter("messToCust");
		String toWhom = request.getParameter("toWhom");
		MessengerDao md = new MessengerDao();
		if (md.sendMessToCust(toWhom, messToCust)) {
			String s = "<p>Reply successful</p>";
			s += "<div class=\"box has-text-centered\">\r\n" +
									"				  	<a href=\"EmployeeHomePage.html\" class=\"button is-primary\">Back</a> \r\n" +  
									"				  	<p id=\"butClicked\"></p>\r\n" +  
									"				</div>";
			PrintWriter pw = response.getWriter();
			pw.println(s);
		}
	}

}
