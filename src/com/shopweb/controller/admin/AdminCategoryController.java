package com.shopweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shopweb.entity.Category;
import com.shopweb.service.CategoryService;
/**
 * ��̨һ���������
 * @author Administrator
 *
 */
@Controller("adminCategoryController")
@RequestMapping("/admin/adminCategory")
public class AdminCategoryController {

	
	@Resource
	private CategoryService categoryService;
	
	//��ѯ����һ������
	@RequestMapping("/findAll")
	public ModelAndView findAll() throws Exception{
		
		List<Category> cList = categoryService.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cList", cList);
		modelAndView.setViewName("../admin/category/list");
		return modelAndView;
	}
	
	@RequestMapping("/add")
	public String add(){
		return "../admin/category/add";
	}
	
	@RequestMapping("/save")
	public String save(Category category){
		categoryService.save(category);
		return "redirect:/admin/adminCategory/findAll";
	}
	
	@RequestMapping("/delete")
	public String delete(Category category){
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "redirect:/admin/adminCategory/findAll";
	}
	
	// �༭һ������ķ���:
	@RequestMapping("/edit")
	public String edit(Category category,Model model){
		category = categoryService.findByCid(category.getCid());
		model.addAttribute("category", category);
		return "../admin/category/edit";
	}
	
	// �޸�һ������ķ���:
	@RequestMapping("/update")
	public String update(Category category){
		categoryService.update(category);
		return "redirect:/admin/adminCategory/findAll";
	}
}
