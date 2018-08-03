package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Room;
import com.revature.util.ConnectionUtil;

public class RoomDao {

	public List<Room> getAllRooms(String location, String type) {
		
		PreparedStatement ps = null;
		Room r = null;
		List<Room> rooms = new ArrayList<Room>();

		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM ROOMS WHERE HOTEL_ID = ? AND ROOM_TYPE = ?";
			ps = conn.prepareStatement(sql);
			if(location.equals("1")) {
				ps.setInt(1,  1);
			} else if (location.equals("2")) {
				ps.setInt(1,  2);
			} else if (location.equals("3")) {
				ps.setInt(1,  3);
			}
			ps.setString(2,  type.toUpperCase());
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
	
	public List<Room> getAllAvailableRooms(String whichLocation) {
		
		PreparedStatement ps = null;
		Room r = null;
		List<Room> rooms = new ArrayList<Room>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM ROOMS WHERE OCCUPIED = ? AND HOTEL_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "N");
			if(whichLocation.equals("1")) {
				ps.setInt(2,  1);
			} else if (whichLocation.equals("2")) {
				ps.setInt(2,  2);
			} else if (whichLocation.equals("3")) {
				ps.setInt(2,  3);
			}
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
	
	public List<Room> getAllGuests(String whichLocation) {
		List<Room> list = new ArrayList<>();
		Room r = null;
		PreparedStatement ps = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM ROOMS WHERE OCCUPIED = ? AND HOTEL_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "Y");
			if(whichLocation.equals("1")) {
				ps.setInt(2,  1);
			} else if (whichLocation.equals("2")) {
				ps.setInt(2,  2);
			} else if (whichLocation.equals("3")) {
				ps.setInt(2,  3);
			}
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int r_id = rs.getInt("room_id");
				int h_id = rs.getInt("hotel_id");
				String room_num = rs.getString("room_num");
				String room_type = rs.getString("room_type");
				double ppn = rs.getInt("price_per_night");
				int max_occ = rs.getInt("max_occupancy");
				String occupied = rs.getString("occupied");
				String email = rs.getString("customer_email");
				
				r = new Room(r_id, h_id, room_num, room_type, ppn, max_occ, occupied, email);
				
				list.add(r);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
