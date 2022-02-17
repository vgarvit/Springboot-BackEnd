package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	@Query(value = "SELECT p FROM Product p WHERE p.name = ?1")
	public Product findByName(String name);
	
	@Query(value = "select cast(sum(quantity) as total from Product")
	public long findProductQuantity();
	
	@Query(value = "select type, sum(quantity)*100/(select sum(quantity) from Product) from Product group by type")
	public List<Object[]> findTypePercent();

}
