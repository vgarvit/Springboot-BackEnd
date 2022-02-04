package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@PostMapping("/addOrderTotal")
	public Order addOrderTotal(@Valid @RequestBody Order order) {
		return orderservice.addOrderTotal(order);
	}

//	@DeleteMapping("/deleteOrder/{id}")
//	public void delete(@PathVariable("id") int id) {
//		orderservice.delete(id);
//	}

//	@PutMapping("/update/{id}")
//    public ResponseEntity<Order> update(@PathVariable("id") int id, @RequestBody Order order) {
//      orderservice.update(id, order);
//      
//      return new ResponseEntity<>(orderservice.getById(id), HttpStatus.OK);
//  }
//	

}
