package com.shopweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.Admin;


/**
 * 登录员登录权限拦截器:
 *
 *
 */
public class AdminLoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否是公开 地址
		if(url.indexOf("adminLogin.action")>=0||url.indexOf("login.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		
		//判断session
		HttpSession session  = request.getSession();
		//从session中取出用户身份信息
		Admin admin = (Admin) session.getAttribute("existAdmin");
		
		if(admin != null){
			//身份存在，放行
			return true;
		}
		
		//执行这里表示用户身份需要认证，跳转登陆页面
		request.setAttribute("loginFirst", "请先登录");
		request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
	
		return false;
		
	}


}