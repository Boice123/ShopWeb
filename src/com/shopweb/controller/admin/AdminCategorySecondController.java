package com.shopweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Category;
import com.shopweb.entity.CategorySecond;
import com.shopweb.service.CategorySecondService;
import com.shopweb.service.CategoryService;
import com.shopweb.utils.PageBean;

@Controller("adminCategorySecond")
@RequestMapping("/admin/adminCategorySecond")
public class AdminCategorySecondController {

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private CategorySecondService categorySecondService;
	
	//查询所有一级分类
	@RequestMapping("/findAll")
	public String findAll(Integer page,Model model) throws Exception{
		
		PageBean<CategorySecond> pagebean = categorySecondService.findByPage(page);
		model.addAttribute("pagebean", pagebean);
		return "../admin/categorysecond/list";
	}
	
	// 跳转到添加二级页面的方法:
	@RequestMapping("/addPage")
	public String addPage(Model model) throws Exception {
		List<Category> cList = categoryService.findAll();
		model.addAttribute("cList", cList);
		return "../admin/categorysecond/add";
	}

	// 添加二级分类的方法:
	@RequestMapping("/save")
	public String save(CategorySecond categorySecond) {
		categorySecondService.save(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
	// 删除二级分类的方法:
	@RequestMapping("delete")
	public String delete(CategorySecond categorySecond) {
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
	// 编辑二级分类的方法:
	@RequestMapping("/edit")
	public String edit(CategorySecond categorySecond,Model model) throws Exception {
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		model.addAttribute("cList", cList);
		model.addAttribute("categorySecond", categorySecond);
		return "../admin/categorysecond/edit";
	}
	
	// 修改二级分类的方法:
	@RequestMapping("/update")
	public String update(CategorySecond categorySecond ){
		categorySecondService.update(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
}
