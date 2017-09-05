package com.shopweb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.Product;
import com.shopweb.service.ProductService;
import com.shopweb.utils.PageBean;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/findByPid")
	public ModelAndView findByPid(Product p){
		
		Product product = productService.findByPid(p.getPid());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("product", product);

		modelAndView.setViewName("product/product");

		return modelAndView;
	}
	
	@RequestMapping("/findByCid")
	public ModelAndView findByCid(Integer cid,PageBean pageBean){
	
		PageBean<Product> pagebean = productService.findByPageCid(cid,pageBean.getPage());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagebean", pagebean);
		modelAndView.addObject("cid", cid);
		modelAndView.setViewName("product/productList");

		return modelAndView;
	}
	
	@RequestMapping("/findByCsid")
	public ModelAndView findByCsid(Integer csid,PageBean pageBean){

		PageBean<Product> pagebean = productService.findByPageCsid(csid,pageBean.getPage());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagebean", pagebean);
		modelAndView.addObject("csid", csid);
		modelAndView.setViewName("product/productList");
		return modelAndView;
	}
}
