package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Product;

public interface IProductService {

	public void addProduct(String products);

	public List<Product> getAllProducts();

	public Product getById(Integer id);
	
	public Product getByName(String name);

	public void delete(String name);
	
	public long getProductCount();
	
	public List<Map<String, Object>> getTypePercent() throws Exception;
	
	public void save(Product product);
	
}
