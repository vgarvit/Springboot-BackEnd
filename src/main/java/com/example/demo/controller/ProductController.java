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
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/getAllProducts") 
	  public ResponseEntity<List<Product>> getAllProducts(){
	   List<Product> list = productservice.getAllProducts();
	   
	   return new ResponseEntity<List<Product>>(list,new HttpHeaders(), HttpStatus.OK);
  }
	
	
	@GetMapping("/getById/{id}")  
	public ResponseEntity<Product> getById(@PathVariable("id") Integer id)
		      throws RuntimeException{
		   Product entity = productservice.getById(id);
	  
	   return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);  
	}
	
	@GetMapping("/getByName")  
	public ResponseEntity<Product> getByName(@PathVariable("name") String name)
		      throws RuntimeException{
		   Product entity = productservice.getByName(name);
	  
	   return new ResponseEntity<Product>(entity, new HttpHeaders(), HttpStatus.OK);  
	}
	
	@PostMapping("/addProduct")
	public void addProduct(@Valid @RequestBody String product) throws RuntimeException{
	 productservice.addProduct(product);
  }
	
	
	@DeleteMapping("/delete/{id}")  
	public void delete(@PathVariable("id") int id){  
		System.out.println("deleted");
		productservice.delete(id);  
	}  
	
	
//	@PutMapping("/update/{id}")
//  public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product product) {
//      productservice.update(id, product);
//      
//      return new ResponseEntity<>(productservice.getById(id), HttpStatus.OK);
//  }
	

}
