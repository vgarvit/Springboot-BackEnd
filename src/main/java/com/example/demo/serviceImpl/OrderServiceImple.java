package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	public List<Order> addOrder(List<OrderDTO> orderDTO, Order order) {
		try {
			List<Order> orderResponse = new ArrayList<>();
			Random rand = new Random();
			int id = rand.nextInt(1000);
			for (OrderDTO o : orderDTO) {
				Product product = productRepository.findById(o.getId()).orElseThrow(null);
				product.setQuantity(product.getQuantity() - o.getQuantity());
				if (product.getQuantity() == 0) {
					product.setStatus("Out Of Stock");
				} else {
					product.setStatus("Available");
				}
				o.setOrd_id(id);
				productRepository.save(product);
				orderResponse.add(orderRepository.save(dtoToEntity(o, product, order)));
			}
			return orderResponse;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public OrderDTO addProductInOrder(String product, OrderDTO orderDTO, String paymentmode) {
		try {
			JSONObject obj = new JSONObject(product);
			Product pro = productRepository.findByName(obj.getString("name"));
			if (pro != null) {
				orderDTO.setId(pro.getId());
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
	public List<Map<String, Object>> getSoldProducts() throws Exception {
		List<Map<String, Object>> addquantity = new ArrayList<>();
		List<Object[]> obj = null;
		obj = orderRepository.findSoldProducts();
		if (obj.contains(null)) {
			throw new Exception("ordered products not found");
		}
		for (Object[] objs : obj) {
			Map<String, Object> addquantity1 = new HashMap<>();
			addquantity1.put("name", objs[0]);
			addquantity1.put("quantity", objs[1]);
			addquantity.add(addquantity1);
		}
		return addquantity;
	}

	private Order dtoToEntity(OrderDTO dto, Product product, Order obj) {
		try {
			Random rand = new Random();
			int id = rand.nextInt(1000);
			obj.setId(product.getId());
			obj.setOrd_id(dto.getOrd_id(id));
			obj.setProduct(product);
			obj.setName(dto.getName());
			obj.setQuantity(dto.getQuantity());
			obj.setPrice(dto.getPrice());
			obj.setTotal(dto.getTotal());
			obj.setPaymentmode(dto.getPaymentmode());
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
	
	@Override
	public long getOrderCount() {
		return orderRepository.findOrderQuantity();
	}

	@Override
	public void delete(int id) {
		orderRepository.deleteById(id);
	}

}
