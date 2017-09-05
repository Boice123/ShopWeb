package com.shopweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.User;

/**
 * 用户登录拦截器
 * @author Administrator
 *
 */
public class UserLoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
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
		if(url.indexOf("login.action")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		
		//判断session
		HttpSession session  = request.getSession();
		//从session中取出用户身份信息
		User user = (User) session.getAttribute("existUser");
		
		if(user != null){
			//身份存在，放行
			return true;
		}
		
		request.setAttribute("loginPlease", "请先登录！");
		//执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/loginUI").forward(request, response);

		return false;
		
	}
}

