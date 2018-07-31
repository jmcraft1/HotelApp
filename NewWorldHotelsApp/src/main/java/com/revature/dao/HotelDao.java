package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Hotel;
import com.revature.util.ConnectionUtil;

public class HotelDao {

	public List<Hotel> getAllHotels() {
		PreparedStatement ps = null;
		Hotel h = null;
		List<Hotel> hotels = new ArrayList<Hotel>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM HOTEL";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int hotel_id = rs.getInt("hotel_id");
				String address = rs.getString("room_num");
				String city = rs.getString("room_num");
				String state = rs.getString("room_type");
				String zip = rs.getString("occupied");
				String phone = rs.getString("room_num");
				int singles = rs.getInt("num_single_rooms");
				int doubles = rs.getInt("num_double_rooms");
				int suites = rs.getInt("num_suites");
				h = new Hotel(hotel_id, address, city, state, zip, phone, singles, doubles, suites);
				hotels.add(h);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return hotels;
	}
	
	
}
