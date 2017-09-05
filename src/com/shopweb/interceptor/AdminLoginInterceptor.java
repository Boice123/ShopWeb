package com.shopweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.Admin;


/**
 * ��¼Ա��¼Ȩ��������:
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
		//��ȡ�����url
		String url = request.getRequestURI();
		//�ж�url�Ƿ��ǹ��� ��ַ
		if(url.indexOf("adminLogin.action")>=0||url.indexOf("login.action")>=0){
			//������е�½�ύ������
			return true;
		}
		
		//�ж�session
		HttpSession session  = request.getSession();
		//��session��ȡ���û������Ϣ
		Admin admin = (Admin) session.getAttribute("existAdmin");
		
		if(admin != null){
			//��ݴ��ڣ�����
			return true;
		}
		
		//ִ�������ʾ�û������Ҫ��֤����ת��½ҳ��
		request.setAttribute("loginFirst", "���ȵ�¼");
		request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
	
		return false;
		
	}


}