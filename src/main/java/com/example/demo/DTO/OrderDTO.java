package com.example.demo.DTO;

import lombok.Data;

@Data
public class OrderDTO {
	private int Id;
	private String name;
	private int price;
	private int quantity;
	private double total;
	private String paymentmode;

}
