package com.example.demo.DTO;

import lombok.Data;

@Data
public class OrderDTO {
	private int Id;
	private String name;
	private int price;
	private int quantity;
	private double total;
	private int ord_id;
	private String paymentmode;
	
	public int getOrd_id(int id) {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
}
