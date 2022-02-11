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
import com.example.demo.entity.Product;
import com.example.demo.service.IOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
@Api(value = "OrderManagement")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@ApiOperation(value = "View a list of orders")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/getAllOrders")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@ApiOperation(value = "Search an Order with an ID", response = Order.class)
	@GetMapping("/getOrderById/{id}")
	public Order getById(@PathVariable("id") Integer id) throws RuntimeException {
		return orderService.getById(id);
	}

	@ApiOperation(value = "Add Products in OrderDTO")
	@PostMapping("/addProductInOrder")
	public OrderDTO addProductInOrder(@RequestBody String product, OrderDTO orderDTO) {
		return orderService.addProductInOrder(product, orderDTO);
	}

	@ApiOperation(value = "Add orders in order table")
	@PostMapping("/addOrder")
	public void addOrder(@Valid @RequestBody List<OrderDTO> orderDTO) {
		orderService.addOrder(orderDTO);
	}

	@DeleteMapping("/deleteOrder/{id}")
	public void deleteOrder(@PathVariable("id") int id) {
		System.out.println("deleted");
		orderService.delete(id);
	}

}
