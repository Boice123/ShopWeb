package com.shopweb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopweb.entity.Admin;
import com.shopweb.repository.AdminRepository;
import com.shopweb.service.AdminService;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminRepository adminRepository;

	public Admin login(Admin admin) {
		return adminRepository.login(admin);
	}
}
