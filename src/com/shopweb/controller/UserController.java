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

	//��¼ҳ����ʾ
	@RequestMapping("/loginUI")
	public String loginUI(){
		return "login";
	}
	
	//��¼����
	@RequestMapping("/login")
	public String login(Model model,HttpSession session, String username, String password,String checkcode)
			throws Exception {
		//�ж���֤�����
		//��session�л����֤������ֵ
		String checkCode1 = (String) session.getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkCode1)){
			if(checkcode==null||checkcode==""){
				model.addAttribute("checkImgError","<h3 color='red'>��֤�벻��Ϊ�գ�</h3>");
			}else{
				model.addAttribute("checkImgError","<h3 color='red'>��֤�����</h3>");
			}
			return "login";
		}
		
		// ����service�����û������֤
		User existUser = userService.login(username, password);

		// ��session�б����û������Ϣ
		if(existUser==null){
			session.setAttribute("loginerror", "�û������������");
			return "redirect:/loginUI.action";
		}
		session.setAttribute("existUser", existUser);
		session.setAttribute("loginFlag", "true");
		// �ض�����Ʒ�б�ҳ��
		return "redirect:/index.action";
	}
	
	//ע��ҳ����ʾ
	@RequestMapping("/registUI")
	public String registUI(){
		return "regist";
	}
	
	//ע�����
	@RequestMapping("/regist")
	public String regist(Model model,HttpSession session,@Validated User user,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			model.addAttribute("allErrors",bindingResult);
			return "regist";
		}
		
		userService.regist(user);
		model.addAttribute("activeRemind", "<h2 color='red'>ע��ɹ����뵽���伤�</h2>");
		return "msg";
	}
	
	//�����û���Ѱ���û�
	@RequestMapping(value="/findUserByUserName",method={RequestMethod.GET})
	public void findUserByUserName(HttpServletResponse response,@RequestParam (value="username",required=true) String username) throws Exception{
		User user = userService.findUserByUserName(username);
		response.setContentType("text/html;charset=utf-8");
		if(user==null){
			if(username.equals("")){
				response.getWriter().println("<font color='red'>�û�������Ϊ��</font>");
			}else if(username.length()<2||username.length()>10){
				response.getWriter().println("<font color='red'>�û�����������2-10֮��</font>");
			}else{
				response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
			}
			
		}else{
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		}
	}
	
	//�û�����
	@RequestMapping("/user_active")
	public String user_active(Model model,User user) throws Exception{
		User existUser = userService.fingByCode(user.getCode());
		if(existUser==null){
			model.addAttribute("registFail", "<h2 color='red'>��������ʧЧ������ʧ�ܣ�������</h2>");
			return "msg";
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			model.addAttribute("registSuccess", "<h2 color='red'>����ɹ������¼</h2>");
			return "msg";
		}
	}
	
	//�û��˳�
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:index";
	}
}