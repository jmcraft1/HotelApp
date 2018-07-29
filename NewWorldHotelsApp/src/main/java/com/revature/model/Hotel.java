package com.revature.model;

public class Hotel {

	private int hotel_id;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private int num_singles;
	private int num_doubles;
	private int num_suites;

	public Hotel(int hotel_id, String address, String city, String state, String zip, String phone, int num_singles,
			int num_doubles, int num_suites) {
		super();
		this.hotel_id = hotel_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.num_singles = num_singles;
		this.num_doubles = num_doubles;
		this.num_suites = num_suites;
	}

	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getNum_singles() {
		return num_singles;
	}

	public void setNum_singles(int num_singles) {
		this.num_singles = num_singles;
	}

	public int getNum_doubles() {
		return num_doubles;
	}

	public void setNum_doubles(int num_doubles) {
		this.num_doubles = num_doubles;
	}

	public int getNum_suites() {
		return num_suites;
	}

	public void setNum_suites(int num_suites) {
		this.num_suites = num_suites;
	}

}
