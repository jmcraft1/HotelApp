package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.revature.model.Reservations;
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
		String room_type = "";
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
				hotel_id = rs.getInt("hotel_id");
				room_type = rs.getString("room_type");
				price = rs.getDouble("price");
				checkIn = rs.getDate("check_in_date");
				checkOut = rs.getDate("check_out_date");
				res = new Reservations(reservation_id, hotel_id, room_type, cust_id, checkIn, checkOut, price);
				rservs.add(res);
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		

		return rservs;
		
	}
	
	public boolean makeReservation(String email, String whichLoc, String whatRoom, String checkIn, String checkOut) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		Date checkInDate = null;
		Date checkOutDate = null;
		System.out.println(checkIn);
		String cid = checkIn.substring(5, 8);
		cid += checkIn.substring(8) + "-";
		cid += checkIn.substring(0, 4);
		String cod = checkOut.substring(5, 8);
		cod += checkOut.substring(8) + "-";
		cod += checkOut.substring(0, 4);
		try {
			checkInDate = sdf1.parse(cid);
			checkOutDate = sdf1.parse(cod);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlCInDate = new java.sql.Date(checkInDate.getTime()); 
		java.sql.Date sqlCOutDate = new java.sql.Date(checkOutDate.getTime());
		
		
		PreparedStatement ps = null;
		Reservations res = null;
		int cust_id = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cust_id = rs.getInt("customer_id");
			}
			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO RESERVATIONS (HOTEL_ID, ROOM_TYPE, CUSTOMER_ID, CHECK_IN_DATE, CHECK_OUT_DATE, PRICE) ";
			sql += "VALUES (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			if (whichLoc.equals("1")) {
				ps.setInt(1, 1);
			} else if (whichLoc.equals("2")) {
				ps.setInt(1,  2);
			} else {
				ps.setInt(1, 3);
			}
			
			ps.setString(2,  whatRoom);
			ps.setInt(3,  cust_id);
			ps.setDate(4, sqlCInDate);
			ps.setDate(5,  sqlCOutDate);
			
			long millies = checkOutDate.getTime() - checkInDate.getTime();
			int nights = (int)TimeUnit.DAYS.convert(millies, TimeUnit.MILLISECONDS);
			Double multiplier = 0.0;
			if (whatRoom.equalsIgnoreCase("single")) {
				multiplier = 100.0;
			} else if (whatRoom.equalsIgnoreCase("double")) {
				multiplier = 150.0;
			} else {
				multiplier = 300.0;
			}
			
			ps.setDouble(6,  multiplier * nights);
			int i = ps.executeUpdate();
			ps.close();
			return (i > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}
	
	
}
	
