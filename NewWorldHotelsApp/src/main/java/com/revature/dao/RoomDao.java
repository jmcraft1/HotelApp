package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Room;
import com.revature.util.ConnectionUtil;

public class RoomDao {

	public List<Room> getAllRooms() {
		PreparedStatement ps = null;
		Room r = null;
		List<Room> rooms = new ArrayList<Room>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ROOM";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int r_id = rs.getInt("room_id");
				int h_id = rs.getInt("hotel_id");
				String room_num = rs.getString("room_num");
				String room_type = rs.getString("room_type");
				double ppn = rs.getInt("price_per_night");
				int max_occ = rs.getInt("max_occupancy");
				String occupied = rs.getString("occupied");
				r = new Room(r_id, h_id, room_num, room_type, ppn, max_occ, occupied);
				rooms.add(r);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rooms;
	}
	
	public List<Room> getAllAvailableRooms() {
		
		PreparedStatement ps = null;
		Room r = null;
		List<Room> rooms = new ArrayList<Room>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ROOM WHERE OCCUPIED = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "Y");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int r_id = rs.getInt("room_id");
				int h_id = rs.getInt("hotel_id");
				String room_num = rs.getString("room_num");
				String room_type = rs.getString("room_type");
				double ppn = rs.getInt("price_per_night");
				int max_occ = rs.getInt("max_occupancy");
				String occupied = rs.getString("occupied");
				r = new Room(r_id, h_id, room_num, room_type, ppn, max_occ, occupied);
				rooms.add(r);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rooms;
	}
}
