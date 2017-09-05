package com.shopweb.service;

import java.util.List;

import com.shopweb.entity.CategorySecond;
import com.shopweb.utils.PageBean;

public interface CategorySecondService {

	public PageBean<CategorySecond> findByPage(Integer page);

	public void save(CategorySecond categorySecond);

	public CategorySecond findByCsId(Integer csid);

	public void delete(CategorySecond categorySecond);

	public void update(CategorySecond categorySecond);

	public List<CategorySecond> findAll();

}
