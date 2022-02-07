package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.IOrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderservice;

	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() {
		return orderservice.getAllOrders();
	}

	@GetMapping("/getOrderById/{id}")
	public Order getById(@PathVariable("id") Integer id) throws RuntimeException {
		return orderservice.getById(id);
	}

	@PostMapping("/addProductInOrder")
	public OrderDTO addProductInOrder(@RequestBody String product, OrderDTO orderDTO) {
		return orderservice.addProductInOrder(product, orderDTO);
	}

	@PostMapping("/addOrder")
	public List<Order> addOrder(@Valid @RequestBody List<OrderDTO> orderDTO){
		return orderservice.addOrder(orderDTO);
	}


}
