package com.example.demo.service;

import java.util.List;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;

public interface IOrderService {
	public List<Order> addOrder(List<OrderDTO> orderDTO);

	public List<Order> getAllOrders();

	public Order getById(Integer id);

	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO);
	
	public void delete(int id);

}
