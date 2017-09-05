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
	
	//��ѯ����һ������
	@RequestMapping("/findAll")
	public String findAll(Integer page,Model model) throws Exception{
		
		PageBean<CategorySecond> pagebean = categorySecondService.findByPage(page);
		model.addAttribute("pagebean", pagebean);
		return "../admin/categorysecond/list";
	}
	
	// ��ת����Ӷ���ҳ��ķ���:
	@RequestMapping("/addPage")
	public String addPage(Model model) throws Exception {
		List<Category> cList = categoryService.findAll();
		model.addAttribute("cList", cList);
		return "../admin/categorysecond/add";
	}

	// ��Ӷ�������ķ���:
	@RequestMapping("/save")
	public String save(CategorySecond categorySecond) {
		categorySecondService.save(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
	// ɾ����������ķ���:
	@RequestMapping("delete")
	public String delete(CategorySecond categorySecond) {
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
	// �༭��������ķ���:
	@RequestMapping("/edit")
	public String edit(CategorySecond categorySecond,Model model) throws Exception {
		categorySecond = categorySecondService.findByCsId(categorySecond.getCsid());
		List<Category> cList = categoryService.findAll();
		model.addAttribute("cList", cList);
		model.addAttribute("categorySecond", categorySecond);
		return "../admin/categorysecond/edit";
	}
	
	// �޸Ķ�������ķ���:
	@RequestMapping("/update")
	public String update(CategorySecond categorySecond ){
		categorySecondService.update(categorySecond);
		return "redirect:/admin/adminCategorySecond/findAll?page=1";
	}
	
}
