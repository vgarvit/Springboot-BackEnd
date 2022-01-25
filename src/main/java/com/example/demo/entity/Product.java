package com.example.demo.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
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
	
	@OneToMany(mappedBy = "product")
	List<Order> order = new ArrayList<>();
	
	@PreRemove
	 public void checkOrderAssociationBeforeRemoval() {
	    if (!this.order.isEmpty()) {
	    	throw new RuntimeException("Can't remove a product that has orders.");
	    }
	 }
	
	@Column(name = "PRODUCT_NAME")
	@NotEmpty(message = "product name should not be empty")
	private String name;
	
	@Column(name = "PRODUCT_QUANTITY", nullable = false)
	@NotNull(message = "product quantity should not be null")
	private int quantity;
	
	@Column(name = "PRODUCT_TYPE", nullable = false)
	@NotEmpty(message = "product type should not be empty")
	private String type;
	
	@Column(name = "PRODUCT_PRICE")
	@NotNull(message = "product price should not be null")
	private int price;
	
	@Column(name = "PRODUCT_MFG")
	@NotNull(message = "product mfg_date should not be null")
	private LocalDate mfg;
	
	@Column(name = "PRODUCT_EXP")
	private LocalDate exp;

	
}
