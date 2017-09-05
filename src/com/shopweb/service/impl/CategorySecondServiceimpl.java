package com.shopweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shopweb.entity.CategorySecond;
import com.shopweb.repository.CategorySecondRepository;
import com.shopweb.service.CategorySecondService;
import com.shopweb.utils.PageBean;
@Service("categorySecondService")
public class CategorySecondServiceimpl implements CategorySecondService {

	@Resource
	private CategorySecondRepository categorySecondRepository;

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = categorySecondRepository.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondRepository.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondRepository.save(categorySecond);
		
	}

	public CategorySecond findByCsId(Integer csid) {
		return categorySecondRepository.findById(csid);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondRepository.delete(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		categorySecondRepository.update(categorySecond);
		
	}

	public List<CategorySecond> findAll() {
		return categorySecondRepository.findAll();
	}
	
	

}
