package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements UserDetailsService{
	
	@Autowired 
	private AdminRepository adminRepository; 
	
	   
	 @Override 
	   public UserDetails loadUserByUsername(String username) 
	   throws UsernameNotFoundException { 
	      Admin admin = adminRepository.findUserByUsername(username);  
	        return admin; 
	   } 
	   public Admin createAdmin(Admin admin) { 
		   Admin admins = new Admin(); 
		   admins.setUsername(admin.getUsername()); 
		   admins.setPassword(admin.getPassword()); 
		   admins.setAccountNonLocked(true); 
		  
		   return adminRepository.save(admin); 
	   } 
	
}
