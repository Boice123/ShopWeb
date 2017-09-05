package com.shopweb.service;

import java.util.List;

import com.shopweb.entity.Category;

public interface CategoryService {

	public List<Category> findAll() throws Exception;

	public void save(Category category);

	public Category findByCid(Integer cid);

	public void delete(Category category);

	public void update(Category category);

}
