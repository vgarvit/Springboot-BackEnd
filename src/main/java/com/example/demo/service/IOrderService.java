package com.example.demo.service;
import java.util.List;
import java.util.Map;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;

public interface IOrderService {
	public List<Order> addOrder(List<OrderDTO> orderDTO, Order order);

	public List<Order> getAllOrders();

	public Order getById(Integer id);

	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO, String paymentMode);
	
	public void delete(int id);
	
	public List<Map<String, Object>> getSoldProducts() throws Exception;
	
	public long getOrderCount();
	
	

}