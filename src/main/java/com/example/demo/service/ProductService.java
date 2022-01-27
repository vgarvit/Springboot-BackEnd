package com.example.demo.service;
import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {
	
	public void addProduct( String product);
	public List<Product> getAllProducts();
	public Product getById(Integer id);
	public Product getByName(String name);
	public void delete(Integer id);
//	public void update(int id, Product product);

}