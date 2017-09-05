package com.shopweb.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Admin;
import com.shopweb.service.AdminService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminService adminService;
	
	@RequestMapping("/login")
	public String loginUI(){
		return "../admin/index";
		
	}
	@RequestMapping("/adminLogin")
	public String adminLogin(HttpSession session,Admin admin,Model model){
		Admin existAdmin = adminService.login(admin);
		if(existAdmin==null){
			model.addAttribute("loginerror","用户名或密码错误");
			return "../admin/index";
		}else{
			session.setAttribute("existAdmin", existAdmin);
			return "../admin/home";
		}
	}
	
	
}
