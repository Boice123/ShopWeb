package com.shopweb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.User;
import com.shopweb.service.UserService;

@Controller("UserController")
public class UserController {
	
	@Resource
	private UserService userService;

	//登录页面显示
	@RequestMapping("/loginUI")
	public String loginUI(){
		return "login";
	}
	
	//登录操作
	@RequestMapping("/login")
	public String login(Model model,HttpSession session, String username, String password,String checkcode)
			throws Exception {
		//判断验证码程序
		//从session中获得验证码的随机值
		String checkCode1 = (String) session.getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkCode1)){
			if(checkcode==null||checkcode==""){
				model.addAttribute("checkImgError","<h3 color='red'>验证码不能为空！</h3>");
			}else{
				model.addAttribute("checkImgError","<h3 color='red'>验证码错误！</h3>");
			}
			return "login";
		}
		
		// 调用service进行用户身份验证
		User existUser = userService.login(username, password);

		// 在session中保存用户身份信息
		if(existUser==null){
			session.setAttribute("loginerror", "用户名或密码错误");
			return "redirect:/loginUI.action";
		}
		session.setAttribute("existUser", existUser);
		session.setAttribute("loginFlag", "true");
		// 重定向到商品列表页面
		return "redirect:/index.action";
	}
	
	//注册页面显示
	@RequestMapping("/registUI")
	public String registUI(){
		return "regist";
	}
	
	//注册操作
	@RequestMapping("/regist")
	public String regist(Model model,HttpSession session,@Validated User user,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors",bindingResult);
			return "regist";
		}
		
		userService.regist(user);
		model.addAttribute("activeRemind", "<h2 color='red'>注册成功，请到邮箱激活！</h2>");
		return "msg";
	}
	
	//根据用户名寻找用户
	@RequestMapping(value="/findUserByUserName",method={RequestMethod.GET})
	public void findUserByUserName(HttpServletResponse response,@RequestParam (value="username",required=true) String username) throws Exception{
		User user = userService.findUserByUserName(username);
		response.setContentType("text/html;charset=utf-8");
		if(user==null){
			if(username.equals("")){
				response.getWriter().println("<font color='red'>用户名不能为空</font>");
			}else if(username.length()<2||username.length()>10){
				response.getWriter().println("<font color='red'>用户名长度需在2-10之间</font>");
			}else{
				response.getWriter().println("<font color='green'>用户名可以使用</font>");
			}
			
		}else{
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}
	}
	
	//用户激活
	@RequestMapping("/user_active")
	public String user_active(Model model,User user) throws Exception{
		User existUser = userService.fingByCode(user.getCode());
		if(existUser==null){
			model.addAttribute("registFail", "<h2 color='red'>激活码已失效，激活失败，请重试</h2>");
			return "msg";
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			model.addAttribute("registSuccess", "<h2 color='red'>激活成功，请登录</h2>");
			return "msg";
		}
	}
	
	//用户退出
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index";
	}
}