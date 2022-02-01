package com.example.demo.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "ADMIN_TABLE")
@Data
public class Admin implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String password;
	private boolean accountNonLocked; 

	 @Override 
	   public boolean isAccountNonExpired() { 
	      return true; 
	   } 
	   @Override
	   public boolean isAccountNonLocked() { 
	      return accountNonLocked; 
	   } 
	   @Override public boolean isCredentialsNonExpired() { 
	      return true; 
	   } 
	   @Override public boolean isEnabled() { 
	   return true; 
	   }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(() -> "read");
	} 


}
