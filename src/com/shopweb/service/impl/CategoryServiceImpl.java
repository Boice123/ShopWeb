package com.shopweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopweb.entity.Category;
import com.shopweb.repository.CategoryRepository;
import com.shopweb.service.CategoryService;
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Resource
	private CategoryRepository categoryRepository;
	
	
	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public void save(Category category) {
		this.categoryRepository.save(category);
	}

	public Category findByCid(Integer cid) {
		return this.categoryRepository.findById(cid);
		
	}

	public void delete(Category category) {
		this.categoryRepository.delete(category);
		
	}

	public void update(Category category) {
		this.categoryRepository.update(category);
	}

}
