package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "PRODUCT_TABLE")
@Data
public class Product {

	@javax.persistence.Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int Id;

	@Column(name = "PRODUCT_NAME")
	@NotEmpty(message = "product name should not be empty")
	private String name;

	@Column(name = "PRODUCT_QUANTITY")
	@NotNull(message = "product quantity should not be null")
	private int quantity;

	@Column(name = "PRODUCT_TYPE")
	@NotEmpty(message = "product type should not be empty")
	private String type;

	@Column(name = "PRODUCT_PRICE")
	@NotNull(message = "product price should not be null")
	private int price;

	@Column(name = "PRODUCT_MFG")
	@NotNull(message = "product mfg_date should not be null")
	private String mfg;

	@Column(name = "PRODUCT_EXP")
	private LocalDate exp;
	
	@Column(name = "STATUS")
	private String status;

}
