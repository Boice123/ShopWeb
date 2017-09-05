package com.shopweb.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.exception.CustomException;
/**
 * ȫ���쳣������
 * 
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		CustomException customException = null;
		if(ex instanceof CustomException){
			customException = (CustomException)ex;
		}else{
			customException = new CustomException("δ֪����");
		}
		
		//������Ϣ
		String message = customException.getMessage();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//��������Ϣ����ҳ��
		modelAndView.addObject("message", message);
		
		//ָ�����ҳ��
		modelAndView.setViewName("error");

		
		return modelAndView;
	}

}
