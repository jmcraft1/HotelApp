package com.revature.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDao {

	public List<Employee> getAllEmployees() {
		PreparedStatement ps = null;
		Employee e = null;
		List<Employee> employees = new ArrayList<Employee>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int emp_id = rs.getInt("employee_id");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String address = rs.getString("room_num");
				String city = rs.getString("room_num");
				String state = rs.getString("room_type");
				String zip = rs.getString("occupied");
				String phone = rs.getString("room_num");
				String email = rs.getString("room_type");
				String password = rs.getString("occupied");
				String authorized = rs.getString("authorized");
				e = new Employee(emp_id, fName, lName, address, city, state, zip, phone, email, password, authorized);
				employees.add(e);
			}

			rs.close();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return employees;
	}
	
	public boolean verifyCred(String email, String pass) {
		
		//System.out.println(pass);
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT PASSWORD FROM EMPLOYEE WHERE EMAIL = ?";
			//String sql = "SELECT PASSWORD, AUTHORIZED FROM EMPLOYEE WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			String e = "";
			//String a = "";
			while (rs.next()) {
				e = rs.getString("password");
				// a = rs.getString("authorized");
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
	
	public boolean verifyManager(String email, String pass) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			//String sql = "SELECT PASSWORD FROM EMPLOYEE WHERE EMAIL = ?";
			String sql = "SELECT PASSWORD, AUTHORIZED FROM EMPLOYEE WHERE EMAIL = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			String e = "";
			String a = "";
			while (rs.next()) {
				e = rs.getString("password");
				a = rs.getString("authorized");
			}
			//System.out.println(e);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			String generatedPassword = sb.toString();
			//System.out.println(generatedPassword);
			return (generatedPassword.equals(e) && a.equals("Y"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
