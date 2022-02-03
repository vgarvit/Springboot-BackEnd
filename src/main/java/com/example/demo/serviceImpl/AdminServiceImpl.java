package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminRepository.findUserByUsername(username);
		return admin;
	}

	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

}
