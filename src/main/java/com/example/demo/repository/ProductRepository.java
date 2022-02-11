package com.example.demo.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	@Query(value = "SELECT p FROM Product p WHERE p.name = ?1")
	public Product findByName(String name);

}
