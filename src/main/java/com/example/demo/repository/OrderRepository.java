package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Order;


@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	@Query(value = "select name, sum(quantity) from Order group by name")
	public List<Object[]> findSoldProducts();
	
	@Query(value = "select sum(quantity) as total from Order")
	public long findOrderQuantity();

}
