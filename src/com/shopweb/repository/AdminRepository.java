package com.shopweb.repository;

import com.shopweb.base.BaseDao;
import com.shopweb.entity.Admin;

public interface AdminRepository extends BaseDao<Admin> {

	public Admin login(Admin admin);

}
