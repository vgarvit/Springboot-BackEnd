package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;

@Entity
@Table(name = "PRODUCT_TABLE")
@Data
public class Product {

	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(notes = "The database generated product ID")
	private int Id;

	@Column(name = "PRODUCT_NAME")
	@NotEmpty(message = "product name should not be empty")
	@ApiModelProperty(notes = "The name of the product")
	private String name;

	@Column(name = "PRODUCT_QUANTITY")
	@NotNull(message = "product quantity should not be null")
	@ApiModelProperty(notes = "The quantity of the product", required = true)
	private int quantity;

	@Column(name = "PRODUCT_TYPE")
	@NotEmpty(message = "product type should not be empty")
	@ApiModelProperty(notes = "The type of the product")
	private String type;

	@Column(name = "PRODUCT_PRICE")
	@NotNull(message = "product price should not be null")
	@ApiModelProperty(notes = "The price of the product", required = true)
	private int price;

	@Column(name = "PRODUCT_MFG")
	@NotNull(message = "product mfg_date should not be null")
	@ApiModelProperty(notes = "The manufacturing date of the product")
	private String mfg;

	@Column(name = "PRODUCT_EXP")
	@ApiModelProperty(notes = "The expiry date of the product")
	private LocalDate exp;

	@Column(name = "PRODUCT_STATUS")
	@ApiModelProperty(notes = "Status of the product if it's available or out of stock")
	private String status;

}
