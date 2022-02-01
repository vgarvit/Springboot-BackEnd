package com.example.demo.service;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;


@Service
@Transactional
public class OrderServiceImple implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Order> getAllOrders(){
		return (List<Order>) orderRepository.findAll();
	}
	
	@Override
	public Order addOrderTotal(Order order) {
		Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(null);
//	    Objects.requireNonNull(product, "You cannot order a non existing product");
	
	    order.setTotal(order.getProduct().getPrice()*order.getQuantity());
		order.setProduct(product);
		product.setQuantity(product.getQuantity() - order.getQuantity());
		order = orderRepository.save(order);
		
		productRepository.save(product);
		
		return orderRepository.save(order);
	} 
	
	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO){
		try {
			JSONObject obj = new JSONObject(product);
			Product pro = productRepository.findByName(obj.getString("name"));
			
			if(pro!=null) {
				orderDTO.setName(obj.getString("name"));
				orderDTO.setQuantity(obj.getInt("quantity"));
				//orderDTO.setTotal(orderDTO.getProduct().getPrice()*orderDTO.getQuantity());
			}else {
				System.out.println("product does not exist");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return orderDTO;
		
	}
	
	@Override
	public Order getById(Integer id) {
    	return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }
	
	@Override
	public void delete(Integer id) {
		orderRepository.deleteById(id);
	}
	
        

}
