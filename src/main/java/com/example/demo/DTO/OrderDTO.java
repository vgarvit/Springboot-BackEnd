package com.example.demo.DTO;

import lombok.Data;

@Data
public class OrderDTO {
	private int Id;
	private String name;
	private int price;
	private int quantity;
	private double total;
	private int ordId;
	private String paymentmode;
	
	public int getOrdId(int id) {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
}
