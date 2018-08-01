package com.revature.dao;

import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDao {

	public List<Customer> getAllCustomers() {
		PreparedStatement ps = null;
		Customer c = null;
		List<Customer> customers = new ArrayList<Customer>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM CUSTOMER";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String address = rs.getString("room_num");
				String city = rs.getString("room_num");
				String state = rs.getString("room_type");
				String zip = rs.getString("occupied");
				String phone = rs.getString("room_num");
				String email = rs.getString("room_type");
				String password = rs.getString("occupied");
				c = new Customer(fName, lName, address, city, state, zip, phone, email, password);
				customers.add(c);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return customers;
	}

	public boolean createCustomer(Customer c) {

		System.out.println("Inside CustomerDao.CreateCustomer: " + c.toString());
		CallableStatement cs = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Entered try");
			conn.setAutoCommit(true);
			String sql = "{CALL SP_INSERT_CUSTOMER (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			cs = conn.prepareCall(sql);
			cs.setString(1, c.getfName());
			cs.setString(2, c.getlName());
			cs.setString(3, c.getAddress());
			cs.setString(4, c.getCity());
			cs.setString(5, c.getState());
			cs.setString(6, c.getZip());
			cs.setString(7, c.getPhone());
			cs.setString(8, c.getEmail());

			
			MessageDigest md = MessageDigest.getInstance("MD5");
			System.out.println(c.getPassword());
			md.update(c.getPassword().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			String generatedPassword = sb.toString();
			
			System.out.println(generatedPassword);
			cs.setString(9, generatedPassword);

			c.setPassword(generatedPassword);
			int i = cs.executeUpdate();
			cs.close();
			return (i > 0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean verifyCred(String email, String pass) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT PASSWORD FROM CUSTOMER WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			String e = "";
			while (rs.next()) {
				e = rs.getString("password");
			}
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			String generatedPassword = sb.toString();
			return (generatedPassword.equals(e));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
}
