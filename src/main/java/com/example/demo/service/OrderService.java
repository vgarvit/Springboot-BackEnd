package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.Order;

public interface OrderService {
	public Order addTotal(Order order);
	public List<Order> getAllOrders();
	public Order getById(Integer id);
	public void delete(Integer id);
	public void update(int id, Order order);
	public Order addOrder(Order order);

}
