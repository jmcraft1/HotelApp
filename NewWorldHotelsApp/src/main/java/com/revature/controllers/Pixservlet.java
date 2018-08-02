package com.revature.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.util.ConnectionUtil;

/**
 * Servlet implementation class Pixservlet
 */
public class Pixservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pixservlet() {
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
		String name = request.getParameter("nameOfPic");
		System.out.println(name);
		InputStream fis = null;
		Part fp = request.getPart("pic");
		
		if(fp != null) {
			fis = fp.getInputStream();
		}
		PreparedStatement ps = null;
		
		/*
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO IMAGES VALUES (?,?)";
			ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setBinaryStream(2, fis, fis.available());
            int i = ps.executeUpdate();
            System.out.println(i+" records affected");
			
			ps.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
		
	}

}
