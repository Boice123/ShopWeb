package com.shopweb.repository;

import com.shopweb.base.BaseDao;
import com.shopweb.entity.User;

public interface UserRepository extends BaseDao<User>{

	/**
	 * 通过用户名和密码查询
	 * @return
	 */
	public User find(String username,String password);

	public User findUserByUserName(String username);

	public User findByCode(String code);
}
