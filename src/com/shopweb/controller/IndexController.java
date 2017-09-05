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
		//查询所有一级目录
		List<Category> categoryList = categoryService.findAll();
		//查询热门商品
		List<Product> hotlist = productService.findHot();
		//查询最新热门商品
		List<Product> newlist = productService.findNew();
		//将一级目录存入session
		session.setAttribute("categoryList", categoryList);
		//将热门商品放在model
		model.addAttribute("hotlist", hotlist);
		//将最新商品放在model
		model.addAttribute("newlist", newlist);
		return "index";
	}
}
