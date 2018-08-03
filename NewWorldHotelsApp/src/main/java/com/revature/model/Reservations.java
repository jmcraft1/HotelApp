package com.revature.model;

import java.util.Date;

public class Reservations {

	private int reservation_id;
	private int hotel_id;
	private String room_type;
	private int cust_id;
	private Date check_in_date;
	private Date check_out_date;
	private double total_price;
	private String status;

	public Reservations(int reservation_id, int hotel_id, String room_type, int cust_id, Date check_in_date,
			Date check_out_date, double total_price, String status) {
		super();
		this.reservation_id = reservation_id;
		this.hotel_id = hotel_id;
		this.room_type = room_type;
		this.cust_id = cust_id;
		this.check_in_date = check_in_date;
		this.check_out_date = check_out_date;
		this.total_price = total_price;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Reservations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public Date getCheck_in_date() {
		return check_in_date;
	}

	public void setCheck_in_date(Date check_in_date) {
		this.check_in_date = check_in_date;
	}

	public Date getCheck_out_date() {
		return check_out_date;
	}

	public void setCheck_out_date(Date check_out_date) {
		this.check_out_date = check_out_date;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

}
