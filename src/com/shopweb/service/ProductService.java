package com.shopweb.service;

import java.util.List;

import com.shopweb.entity.Product;
import com.shopweb.utils.PageBean;



public interface ProductService {

	public List<Product> findHot();

	public List<Product> findNew();

	public Product findByPid(Integer pid);

	public PageBean<Product> findByPageCid(Integer cid, int page);

	public PageBean<Product> findByPageCsid(Integer csid, int page);

	public PageBean<Product> findByPage(Integer page);

	public void save(Product product);

	public void delete(Product product);

	public void update(Product product);


	


}
