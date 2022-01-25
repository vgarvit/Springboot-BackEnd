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
				    existp.setMfg((LocalDate) obj.get("mfg"));
				    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				    Date date = sdf.parse(obj.get("mfg").toString());
				    System.out.println(date);
				    String year = Integer.toString(existp.getMfg().getYear()+2);
			        String month = Integer.toString(existp.getMfg().getMonthValue());
			        if(Integer.parseInt(month)<10) {
			        	month = "0"+month;
			        }
			        String day = Integer.toString(existp.getMfg().getDayOfMonth());
			        LocalDate dates = LocalDate.parse(year+"-"+month+"-"+day);
			        existp.setExp(dates);
			        productrepository.save(existp);
				
			}else {
				Product pro = new Product();
				pro.setName(obj.getString("name"));
				pro.setQuantity(obj.getInt("quantity"));
				pro.setType(obj.getString("type"));
				pro.setPrice(obj.getInt("price"));
//				pro.setMfg((LocalDate)obj.get("mfg"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(obj.get("mfg").toString());
				    System.out.println(date);
				String year = Integer.toString(pro.getMfg().getYear()+2);
		        String month = Integer.toString(pro.getMfg().getMonthValue());
		        if(Integer.parseInt(month)<10) {
		        	month = "0"+month;
		        }
		        String day = Integer.toString(pro.getMfg().getDayOfMonth());
		        LocalDate dates = LocalDate.parse(year+"-"+month+"-"+day);
		        pro.setExp(dates);
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
	
//	@Override
//	public void update(int id, Product product) {
//		try {
//			Product products = productrepository.findById(id).get();
//	        System.out.println(product.toString());
//	        products.setName(product.getName());
//	        products.setQuantity(product.getQuantity());
//	        products.setType(product.getType());
//	        products.setPrice(product.getPrice());
//	        products.setMfg(product.getMfg());
//	        String year = Integer.toString(product.getMfg().getYear()+2);
//	        String month = Integer.toString(product.getMfg().getMonthValue());
//	        if(Integer.parseInt(month)<10) {
//	        	month = "0"+month;
//	        }
//	        String day = Integer.toString(product.getMfg().getDayOfMonth());
//	        LocalDate dates = LocalDate.parse(year+"-"+month+"-"+day);
//	        products.setExp(dates);
//	        productrepository.save(products);
//	    } catch (Exception e) {
//			System.out.println(e);
//		}
//        
//	}
}	
