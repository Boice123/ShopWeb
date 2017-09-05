package com.shopweb.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shopweb.entity.User;
import com.shopweb.repository.UserRepository;
import com.shopweb.service.UserService;
import com.shopweb.utils.MailUitls;
import com.shopweb.utils.UUIDUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository; 
	
	
	public User login(String username, String password) {	
		return this.userRepository.find(username, password);
	}

	public void regist(User user) {
		user.setState(0);
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		this.userRepository.save(user);
		//发送激活邮件
		MailUitls.sendMail(user.getEmail(), code);
		
	}

	public User findUserByUserName(String username) {
		return this.userRepository.findUserByUserName(username);
		
	}

	
	public User fingByCode(String code) {
		return this.userRepository.findByCode(code);
	}

	//修改用户状态
	public void update(User existUser) {
		this.userRepository.update(existUser);
		
	}

}
