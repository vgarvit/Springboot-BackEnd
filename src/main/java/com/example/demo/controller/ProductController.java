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
import com.example.demo.entity.Product;
import com.example.demo.service.IProductService;

@CrossOrigin("*")
@RestController
//@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productservice;

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productservice.getAllProducts();
	}

	@GetMapping("/getProductById/{id}")
	public Product getById(@PathVariable("id") Integer id) throws RuntimeException {
		return productservice.getById(id);
	}

	@GetMapping("/getByName")
	public Product getByName(@PathVariable("name") String name) throws RuntimeException {
		return productservice.getByName(name);
	}

	@PostMapping("/addProduct")
	public void addProduct(@Valid @RequestBody String product) throws RuntimeException {
		productservice.addProduct(product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public void delete(@PathVariable("id") int id) {
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
