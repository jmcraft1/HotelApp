package com.revature.model;

public class Room {

	private int room_id;
	private int hotel_id;
	private String room_num;
	private String room_type;
	private double price_per_night;
	private int max_occupancy;
	private String occupied;

	public Room(int room_id, int hotel_id, String room_num, String room_type, double price_per_night, int max_occupancy,
			String occupied) {
		super();
		this.room_id = room_id;
		this.hotel_id = hotel_id;
		this.room_num = room_num;
		this.room_type = room_type;
		this.price_per_night = price_per_night;
		this.max_occupancy = max_occupancy;
		this.occupied = occupied;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getRoom_num() {
		return room_num;
	}

	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public double getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(double price_per_night) {
		this.price_per_night = price_per_night;
	}

	public int getMax_occupancy() {
		return max_occupancy;
	}

	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}

	public String getOccupied() {
		return occupied;
	}

	public void setOccupied(String occupied) {
		this.occupied = occupied;
	}

}
