package com.example.demo.entity;
//import java.util.Collection;
//import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Table(name = "ADMIN_TABLE")
@Data
public class Admin {

	@Id
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;

}
