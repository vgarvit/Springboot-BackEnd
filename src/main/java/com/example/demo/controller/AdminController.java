package com.example.demo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Admin;
import com.example.demo.service.AdminServiceImpl;

@RestController
public class AdminController {
	   
	   @Autowired
	   private AdminServiceImpl adminServiceImpl;
	
	   
	   @GetMapping("/login") 
	   public String login(HttpServletRequest request, HttpSession session) { 
	      session.setAttribute(
	         "error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION")
	      ); 
	      return "login"; 
	   } 
	   
	   @PostMapping("/createAdmin")
	   public Admin createAdmin(@RequestBody Admin admin) {
	     Admin response = adminServiceImpl.createAdmin(admin);
		  
	     return response;
	   }
		   
	   
	   private String getErrorMessage(HttpServletRequest request, String key) {
		      Exception exception = (Exception) request.getSession().getAttribute(key); 
		      String error = ""; 
		      if (exception instanceof BadCredentialsException) { 
		         error = "Invalid username and password!"; 
		      } else if (exception instanceof LockedException) { 
		         error = exception.getMessage(); 
		      } else { 
		         error = "Invalid username and password!"; 
		      } 
		      return error;
		   }

}
