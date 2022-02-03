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
import lombok.Data;

@Entity
@Data
@Table(name = "ORDER_TABLE")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ORDER_ID")
	private int id;

	@ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_FK", nullable = false)
	private Product product;

	@Column(name = "PRODUCT_NAME")
	@NotEmpty(message = "product name cannot be empty")
	private String name;

	@Column(name = "PRODUCT_QUANTITY")
	@NotNull(message = "product name cannot be empty")
	private int quantity;

	@Column(name = "PRODUCT_PRICE")
	@NotNull(message = "product name cannot be empty")
	private int price;

	@Column(name = "PAYMENT_MODE")
	@NotEmpty(message = "mode of payment cannot be empty")
	private String paymentmode;

	@Column(name = "TOTAL_PRICE")
	private int total;

}
