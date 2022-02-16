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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
@Api(value = "OrderManagement")
public class ProductController {

	@Autowired
	private IProductService productService;

	@ApiOperation(value = "View a list of all products")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@ApiOperation(value = "Search a product with an ID", response = Product.class)
	@GetMapping("/getProductById/{id}")
	public Product getById(@PathVariable("id") Integer id) throws RuntimeException {
		return productService.getById(id);
	}
	
	@GetMapping("/getCount")
	public long getOrderCount() {
		return productService.getProductCount();
	}

	@ApiOperation(value = "Add a product")
	@PostMapping("/addProduct")
	public void addProduct(@Valid @RequestBody String product) throws RuntimeException {
		productService.addProduct(product);
	}

	@ApiOperation(value = "Delete a product")
	@DeleteMapping("/deleteProduct")
	public void delete(@RequestBody String product) {
		productService.delete(product);
	}

}
