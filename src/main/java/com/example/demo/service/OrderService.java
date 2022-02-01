package com.example.demo.service;
import java.util.List;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;


public interface OrderService {
	public Order addOrderTotal(Order order);
	public List<Order> getAllOrders();
	public Order getById(Integer id);
	public void delete(Integer id);
	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO);

}
