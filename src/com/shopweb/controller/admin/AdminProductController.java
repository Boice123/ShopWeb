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
		
		//ԭʼ����
		String originalFilename = upload.getOriginalFilename();
		//�ϴ�ͼƬ
		if(upload!=null && originalFilename!=null && originalFilename.length()>0){
					
			String pic_path = request.getSession().getServletContext().getRealPath("/")+"products/pro/";
					
			//�µ�ͼƬ����
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//��ͼƬ
			File newFile = new File(pic_path+newFileName);
			
			//���ڴ��е�����д�����
			upload.transferTo(newFile);
			
			//����ͼƬ����д��itemsCustom��
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
		// ɾ�����ݿ�����Ʒ��¼:
		productService.delete(product);

		return "redirect:/admin/adminProduct/findAll?page=1";
	}
	
	// �༭��Ʒ�ķ���
	@RequestMapping("/edit")
	public String edit(Product product,Model model) {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		model.addAttribute("csList", csList);
		model.addAttribute("product", product);
		return "../admin/product/edit";
	}

	// �޸���Ʒ�ķ���
	@RequestMapping("/update")
	public String update(Product product,HttpServletRequest request,MultipartFile upload) throws Exception {
		// ����Ϣ�޸ĵ����ݿ�
		product.setPdate(new Date());
		
		// �ϴ�:
		if(upload != null ){
			//ɾ��ԭ��ͼƬ
			String delPath = request.getSession().getServletContext().getRealPath("/") + product.getImage();
			File file = new File(delPath);
			file.delete();
			
			String originalFilename = upload.getOriginalFilename();
			String pic_path = request.getSession().getServletContext().getRealPath("/")+"products/pro/";
			
			//�µ�ͼƬ����
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

			File newFile = new File(pic_path+newFileName);
			
			//���ڴ��е�����д�����
			upload.transferTo(newFile);

			product.setImage("products/pro/"+newFileName);
		}
		productService.update(product);
		
		return "redirect:/admin/adminProduct/findAll?page=1";
	}
}
