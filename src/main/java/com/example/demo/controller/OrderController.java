package com.example.demo.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderservice;
	
	@GetMapping("/getAllOrders") 
	  public ResponseEntity<List<Order>> getAllOrders(){
	   List<Order> list = orderservice.getAllOrders();
	   
	   return new ResponseEntity<List<Order>>(list,new HttpHeaders(), HttpStatus.OK);
  }
	
	
	@GetMapping("/getById/{id}")  
	public ResponseEntity<Order> getById(@PathVariable("id") Integer id)
		      throws RuntimeException{
		   Order entity = orderservice.getById(id);
	  
	   return new ResponseEntity<Order>(entity, new HttpHeaders(), HttpStatus.OK);  
	}  
	  
	
//	@PostMapping("/addOrder")
//  public Order addOrder(@Valid @RequestBody Order order) {
//	 Order orderResponse = orderservice.addOrder(order);
//	   return orderResponse;
//  }
	
	
	@DeleteMapping("/delete/{id}")  
	public void delete(@PathVariable("id") int id){  
		System.out.println("deleted");
		orderservice.delete(id);  
	}  
	
	
	
//	@PutMapping("/update/{id}")
//    public ResponseEntity<Order> update(@PathVariable("id") int id, @RequestBody Order order) {
//      orderservice.update(id, order);
//      
//      return new ResponseEntity<>(orderservice.getById(id), HttpStatus.OK);
//  }
//	


}
