package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
	

	 @Override 
	   protected void configure(HttpSecurity http) throws Exception { 
	      http 
	      .csrf().disable()
	      .authorizeRequests().antMatchers("/createAdmin")
	      .permitAll() .anyRequest().authenticated() 
	      .and()
	      .formLogin() .loginPage("/login")
	      .permitAll() 
	      .and() 
	      .logout() .invalidateHttpSession(true) 
	      .clearAuthentication(true) .permitAll(); 
	   }

}
