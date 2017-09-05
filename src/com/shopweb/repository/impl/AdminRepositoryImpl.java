package com.shopweb.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.Admin;
import com.shopweb.repository.AdminRepository;
@Repository("adminRepository")
public class AdminRepositoryImpl extends BaseDaoImpl<Admin> implements
		AdminRepository {

	public Admin login(Admin admin) {
		String hql = "from Admin where username=? and password=?";
		List<Admin> list = this.getHibernateTemplate().find(hql,admin.getUsername(),admin.getPassword());
		if(list!= null &&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
}
