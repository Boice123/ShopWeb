package com.shopweb.service;

import com.shopweb.entity.User;

public interface UserService {

	public User login(String username, String password) throws Exception;

	public void regist(User user) throws Exception;

	public User findUserByUserName(String username) throws Exception;

	public User fingByCode(String code) throws Exception;

	public void update(User existUser) throws Exception;
}
