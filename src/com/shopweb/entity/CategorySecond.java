package com.shopweb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 二级分类的实体
 *
 */
public class CategorySecond {
	private Integer csid;
	private String csname;
	// 存一级分类的对象.
	private Category category;
	// 配置商品集合
	private Set<Product> products = new HashSet<Product>();

	
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
