package com.example.demo.service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImple implements ProductService{
	
	@Autowired
	private ProductRepository productrepository;
	
	@Override
	public List<Product> getAllProducts(){
		return (List<Product>) productrepository.findAll();
	}
	
	@Override
	public void addProduct(String products) {
		try {
			JSONObject obj = new JSONObject(products);
			
			Product product = productrepository.findByName(obj.getString("name"));
			
			if(product!=null){
				    Integer quantity = product.getQuantity() + obj.getInt("quantity");
				    product.setQuantity(quantity);
				    product.setType(obj.getString("type"));
				    product.setPrice(obj.getInt("price"));
				    product.setMfg(obj.getString("mfg"));
				    Date mfg = new SimpleDateFormat("dd-MM-yyyy").parse(product.getMfg());
				    Calendar c = Calendar.getInstance();
				    c.setTime(mfg);
				    c.add(Calendar.YEAR, 2);
				    Date exp = c.getTime();
				    LocalDate date = exp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				    product.setExp(date);
			        productrepository.save(product);
			}else {
				Product pro = new Product(); 
				pro.setName(obj.getString("name"));
				pro.setQuantity(obj.getInt("quantity"));
				pro.setType(obj.getString("type"));
				pro.setPrice(obj.getInt("price"));
				pro.setMfg(obj.getString("mfg"));
				Date mfg = new SimpleDateFormat("dd-MM-yyyy").parse(pro.getMfg());
				Calendar c = Calendar.getInstance();
			    c.setTime(mfg);
			    c.add(Calendar.YEAR, 2);
			    Date exp = c.getTime();
			    LocalDate date = exp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			    pro.setExp(date);
		        productrepository.save(pro);
				}
		} catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	@Override
	public Product getById(Integer id) {
    	return productrepository.findById(id).orElseThrow(RuntimeException::new);
    }
	
	@Override
	public Product getByName(String name) {
    	return productrepository.findByName(name);
    }
	
	@Override
	public void delete(Integer id) {
		productrepository.deleteById(id);
	}
	
}	
