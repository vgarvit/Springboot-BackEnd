package com.example.demo.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.IOrderService;

@Service
@Transactional
public class OrderServiceImple implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Order> getAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public Order addOrderTotal(Order order) {
		Product product = productRepository.findById(order.getProduct().getId()).orElseThrow(null);
		order.setTotal(order.getProduct().getPrice() * order.getQuantity());
		order.setProduct(product);
		product.setQuantity(product.getQuantity() - order.getQuantity());
		order = orderRepository.save(order);

		productRepository.save(product);

		return orderRepository.save(order);
	}

	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO) {
		try {
			JSONObject obj = new JSONObject(product);
			Product pro = productRepository.findByName(obj.getString("name"));

			if (pro != null) {
				orderDTO.setName(obj.getString("name"));
				orderDTO.setQuantity(obj.getInt("quantity"));
				if (pro.getQuantity() < orderDTO.getQuantity()) {
					throw new Exception("Quantity is more than available quantity");
				} else {
					orderDTO.setPrice(pro.getPrice());
					orderDTO.setTotal(pro.getPrice() * orderDTO.getQuantity());
				}
			} else {
				System.out.println("product not exist");
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
	public void delete(OrderDTO orderDTO) {

	}

}
