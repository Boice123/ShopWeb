package com.shopweb.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.shopweb.entity.CategorySecond;
import com.shopweb.entity.Product;
import com.shopweb.service.CategorySecondService;
import com.shopweb.service.ProductService;
import com.shopweb.utils.PageBean;

@Controller("adminProductController")
@RequestMapping("/admin/adminProduct")
public class AdminProductController {

	@Resource
	private ProductService productService;
	
	@Resource
	private CategorySecondService categorySecondService;

	
	@RequestMapping("/findAll")
	public String findAll(Integer page,Model model){
		PageBean<Product> pageBean = productService.findByPage(page);
		model.addAttribute("pageBean", pageBean);
		return "../admin/product/list";
	}
	
	@RequestMapping("/addPage")
	public String addPage(Model model){
		List<CategorySecond> csList = categorySecondService.findAll();
		model.addAttribute("csList", csList);
		return "../admin/product/add";
	}
	
	@RequestMapping("/save")
	public String save(Product product,HttpServletRequest request,MultipartFile upload) throws Exception{
		product.setPdate(new Date());
		
		//原始名称
		String originalFilename = upload.getOriginalFilename();
		//上传图片
		if(upload!=null && originalFilename!=null && originalFilename.length()>0){
					
			String pic_path = request.getSession().getServletContext().getRealPath("/")+"products/pro/";
					
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新图片
			File newFile = new File(pic_path+newFileName);
			
			//将内存中的数据写入磁盘
			upload.transferTo(newFile);
			
			//将新图片名称写到itemsCustom中
			product.setImage("products/pro/"+newFileName);
			
		}
		
		productService.save(product);
		return "redirect:/admin/adminProduct/findAll?page=1";
	}
	
	@RequestMapping("/delete")
	public String delete(Product product,HttpServletRequest request){
		product = productService.findByPid(product.getPid());
		String path =request.getSession().getServletContext().getRealPath(
				"/" + product.getImage());
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);

		return "redirect:/admin/adminProduct/findAll?page=1";
	}
	
	// 编辑商品的方法
	@RequestMapping("/edit")
	public String edit(Product product,Model model) {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		model.addAttribute("csList", csList);
		model.addAttribute("product", product);
		return "../admin/product/edit";
	}

	// 修改商品的方法
	@RequestMapping("/update")
	public String update(Product product,HttpServletRequest request,MultipartFile upload) throws Exception {
		// 将信息修改到数据库
		product.setPdate(new Date());
		
		// 上传:
		if(upload != null ){
			//删除原有图片
			String delPath = request.getSession().getServletContext().getRealPath("/") + product.getImage();
			File file = new File(delPath);
			file.delete();
			
			String originalFilename = upload.getOriginalFilename();
			String pic_path = request.getSession().getServletContext().getRealPath("/")+"products/pro/";
			
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

			File newFile = new File(pic_path+newFileName);
			
			//将内存中的数据写入磁盘
			upload.transferTo(newFile);

			product.setImage("products/pro/"+newFileName);
		}
		productService.update(product);
		
		return "redirect:/admin/adminProduct/findAll?page=1";
	}
}
