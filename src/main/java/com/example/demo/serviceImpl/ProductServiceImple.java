package com.example.demo.serviceImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.IProductService;

@Service
@Transactional
public class ProductServiceImple implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public void addProduct(String products) {
		try {
			JSONObject obj = new JSONObject(products);

			Product product = productRepository.findByName(obj.getString("name"));

			if (product != null) {
				Integer quantity = product.getQuantity() + obj.getInt("quantity");
				product.setQuantity(quantity);
				product.setType(obj.getString("type"));
				product.setPrice(obj.getInt("price"));
				product.setMfg(obj.getString("mfg"));
				Date mfg = new SimpleDateFormat("yyyy-MM-dd").parse(product.getMfg());
				Calendar c = Calendar.getInstance();
				c.setTime(mfg);
				c.add(Calendar.YEAR, 2);
				Date date = c.getTime();
				LocalDate exp = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    product.setExp(exp);
				if (product.getQuantity() == 0) {
					product.setStatus("Out Of Stock");
				} else {
					product.setStatus("Available");
				}
				productRepository.save(product);
			} else {
				Product pro = new Product();
				pro.setName(obj.getString("name"));
				pro.setQuantity(obj.getInt("quantity"));
				pro.setType(obj.getString("type"));
				pro.setPrice(obj.getInt("price"));
				pro.setMfg(obj.getString("mfg"));
				Date mfg = new SimpleDateFormat("yyyy-MM-dd").parse(pro.getMfg());
				Calendar c = Calendar.getInstance();
				c.setTime(mfg);
				c.add(Calendar.YEAR, 2);
				Date date = c.getTime();
				LocalDate exp = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    pro.setExp(exp);
				if (pro.getQuantity() == 0) {
					pro.setStatus("Out Of Stock");
				} else {
					pro.setStatus("Available");
				}
				productRepository.save(pro);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.findById(id).orElseThrow(RuntimeException::new);
	}
    
	@Override
	public List<Map<String, Object>> getTypePercent() throws Exception {
		List<Map<String, Object>> percent = new ArrayList<>();
		List<Object[]> obj = null;
		obj = productRepository.findTypePercent();
		if (obj.contains(null)) {
			throw new Exception("Product type is not available");
		}
		for (Object[] objs : obj) {
			Map<String, Object> percent1 = new HashMap<>();
			percent1.put("name", objs[0]);
			percent1.put("y", objs[1]);
			percent.add(percent1);
		}
		return percent;
	}
	
	@Override
	public long getProductCount() {
		return productRepository.findProductQuantity();
	}
	
	@Override
	public void delete(String product) {
		try {
			JSONObject obj = new JSONObject(product);
			Product pro = productRepository.findByName(obj.getString("name"));
			productRepository.delete(pro);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
