package com.shopweb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Category;
import com.shopweb.entity.Product;
import com.shopweb.service.CategoryService;
import com.shopweb.service.ProductService;

@Controller
public class IndexController {

	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;
	
	
	@RequestMapping("/index")
	public String index(Model model,HttpSession session) throws Exception{
		//��ѯ����һ��Ŀ¼
		List<Category> categoryList = categoryService.findAll();
		//��ѯ������Ʒ
		List<Product> hotlist = productService.findHot();
		//��ѯ����������Ʒ
		List<Product> newlist = productService.findNew();
		//��һ��Ŀ¼����session
		session.setAttribute("categoryList", categoryList);
		//��������Ʒ����model
		model.addAttribute("hotlist", hotlist);
		//��������Ʒ����model
		model.addAttribute("newlist", newlist);
		return "index";
	}
}
