package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "ORDER_TABLE")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ORDER_PK")
	@ApiModelProperty(notes = "Auto generated ID as Primary key")
	private int id;
    
	@ApiModelProperty(notes = "The Order Id used for differentiating orders ")
	@Column(name = "ORDER_ID")
	private int ord_id;

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_FK", nullable = false)
	@ApiModelProperty(notes = "Variable of product type used for mapping between tables")
	private Product product;

	@Column(name = "PRODUCT_NAME")
	@NotEmpty(message = "product name cannot be empty")
	@ApiModelProperty(notes = "The name of selected product for order")
	private String name;

	@Column(name = "PRODUCT_QUANTITY")
	@NotNull(message = "product name cannot be null")
	@ApiModelProperty(notes = "Selected product quantity for order")
	private int quantity;

	@Column(name = "PRODUCT_PRICE")
	@NotNull(message = "product name cannot be null")
	@ApiModelProperty(notes = "The price of selected product for order")
	private int price;

	@Column(name = "PAYMENT_MODE")
	@ApiModelProperty(notes = "The mode of payment used by customer")
	private String paymentmode;

	@Column(name = "TOTAL_PRICE")
	@ApiModelProperty(notes = "The total price of products in order")
	private double total;

}
