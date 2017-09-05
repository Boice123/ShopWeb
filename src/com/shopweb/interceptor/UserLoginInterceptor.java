package com.shopweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.User;

/**
 * �û���¼������
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
		//��ȡ�����url
		String url = request.getRequestURI();
		//�ж�url�Ƿ��ǹ��� ��ַ
		if(url.indexOf("login.action")>=0){
			//������е�½�ύ������
			return true;
		}
		
		//�ж�session
		HttpSession session  = request.getSession();
		//��session��ȡ���û������Ϣ
		User user = (User) session.getAttribute("existUser");
		
		if(user != null){
			//��ݴ��ڣ�����
			return true;
		}
		
		request.setAttribute("loginPlease", "���ȵ�¼��");
		//ִ�������ʾ�û������Ҫ��֤����ת��½ҳ��
		request.getRequestDispatcher("/loginUI").forward(request, response);

		return false;
		
	}
}

