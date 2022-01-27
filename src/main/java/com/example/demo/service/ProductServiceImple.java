package com.example.demo.service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	public void addProduct(String product) {
		try {
			JSONObject obj = new JSONObject(product);
			
			Product existp = productrepository.findByName(obj.getString("name"));
			
			if(existp!=null){
				    existp.setQuantity(obj.getInt("quantity"));
				    existp.setType(obj.getString("type"));
				    existp.setPrice(obj.getInt("price"));
				    existp.setMfg(obj.getString("mfg"));
				    SimpleDateFormat mfg = new SimpleDateFormat(existp.getMfg());
				    mfg.format(new Date());
				    existp.setExp(obj.getString("exp"));
				    SimpleDateFormat exp = new SimpleDateFormat(existp.getExp());
				    exp.format(new Date());
			        productrepository.save(existp);
			}else {
				Product pro = new Product();
				pro.setName(obj.getString("name"));
				pro.setQuantity(obj.getInt("quantity"));
				pro.setType(obj.getString("type"));
				pro.setPrice(obj.getInt("price"));
				pro.setMfg(obj.getString("mfg"));
				SimpleDateFormat mfg = new SimpleDateFormat(pro.getMfg());
			    mfg.format(new Date());
			    pro.setExp(obj.getString("exp"));
			    SimpleDateFormat exp = new SimpleDateFormat(pro.getExp());
			    exp.format(new Date());
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
