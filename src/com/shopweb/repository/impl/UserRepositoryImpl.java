package com.shopweb.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.User;
import com.shopweb.repository.UserRepository;
@Repository("userRepository") 
public class UserRepositoryImpl extends BaseDaoImpl<User> implements UserRepository{

	//µÇÂ¼ÑéÖ¤
	public User find(String username, String password) {
		List<User> allUser = this.getHibernateTemplate().find("from User where username=? and password = ?", username,password);
		if(allUser!=null&&allUser.size()>0){
			return allUser.get(0);
		} 
		return null;
	}

	public User findUserByUserName(String username) {
		List<User> allUser = this.getHibernateTemplate().find("from User where username=?", username);
		if(allUser!=null&&allUser.size()>0){
			return allUser.get(0);
		} 
		return null;
	}

	public User findByCode(String code) {
		List<User> allUser = this.getHibernateTemplate().find("from User where code=?", code);
		if(allUser!=null&&allUser.size()>0){
			return allUser.get(0);
		} 
		return null;
	}

}
