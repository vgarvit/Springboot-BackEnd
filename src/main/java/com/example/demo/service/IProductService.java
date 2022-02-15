package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface IProductService {

	public void addProduct(String products);

	public List<Product> getAllProducts();

	public Product getById(Integer id);

	public Product getByName(String name);

	public void delete(String name);
	
}
