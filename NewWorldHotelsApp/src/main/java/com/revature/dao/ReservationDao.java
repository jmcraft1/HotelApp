package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.model.Reservations;
import com.revature.model.Room;
import com.revature.util.ConnectionUtil;

public class ReservationDao {

	public List<Reservations> getMyReservations(String email) {
		
		PreparedStatement ps = null;
		Reservations res = null;
		List<Reservations> rservs = new ArrayList<Reservations>();
		int cust_id = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT CUST_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cust_id = rs.getInt("cust_id");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		int hotel_id = 0;
		int room_id = 0;
		double price = 0;
		Date checkIn = null;
		Date checkOut = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM RESERVATIONS WHERE CUST_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cust_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cust_id = rs.getInt("customer_id");
				int reservation_id = rs.getInt("reservation_id");
				String room_num = rs.getString("room_num");
				hotel_id = rs.getInt("hotel_id");
				room_id = rs.getInt("room_id");
				price = rs.getDouble("price");
				checkIn = rs.getDate("check_in_date");
				checkOut = rs.getDate("check_out_date");
				res = new Reservations(reservation_id, hotel_id, room_id, room_num, cust_id, checkIn, checkOut, price);
				rservs.add(res);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		

		return rservs;
		
	}
}
	
