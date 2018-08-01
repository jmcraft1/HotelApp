package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Messages;
import com.revature.model.Room;
import com.revature.util.ConnectionUtil;

public class MessengerDao {

	public boolean sendMessage (String email, String message) {
		
		PreparedStatement ps = null;
		
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO MESSAGES VALUES (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,  email);
			ps.setString(2,  "Host");
			ps.setString(3,  message);
			int i = ps.executeUpdate();
			ps.close();
			return (i > 0);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
		
	}
	
	public List<Messages> getMessages(String email) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Messages> list = new ArrayList<>();
		System.out.println("entered messagedao with email: " + email);
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM MESSAGES WHERE MESS_FROM = ? OR MESS_TO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				String from = rs.getString("Mess_from");
				String to = rs.getString("Mess_to");
				String message = rs.getString("message");
				
				Messages m = new Messages(to, from, message);
				System.out.println(m.toString());
				list.add(m);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
		
	}
}
