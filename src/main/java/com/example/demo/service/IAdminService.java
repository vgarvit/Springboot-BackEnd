package com.example.demo.service;

import com.example.demo.entity.Admin;

public interface IAdminService {

	public Admin loadUserByUsername(String username);

	public Admin createAdmin(Admin admin);

}
