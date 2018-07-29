package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.model.Reservations;
import com.revature.util.ConnectionUtil;

public class ReservationDao {

	public List<Reservations> getAllReservations() {
		PreparedStatement ps = null;
		Reservations r = null;
		List<Reservations> reservations = new ArrayList<Reservations>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM RESERVATIONS";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int res_id = rs.getInt("reservation_id");
				int hotel_id = rs.getInt("hotel_id");
				int room_id = rs.getInt("room_id");
				String room_num = rs.getString("room_num");
				int cus_id = rs.getInt("customer_id");
				Date checkInDate = rs.getDate("check_in_date");
				Date checkOutDate = rs.getDate("check_out_date");
				double price = rs.getDouble("price");
				String mop = rs.getString("method_of_payment");
				
				r = new Reservations(res_id, hotel_id, room_id, room_num, cus_id, checkInDate, checkOutDate, price, mop);
				reservations.add(r);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return reservations;
	}
}
