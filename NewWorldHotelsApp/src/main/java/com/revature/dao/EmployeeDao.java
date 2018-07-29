package com.revature.dao;

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
}
