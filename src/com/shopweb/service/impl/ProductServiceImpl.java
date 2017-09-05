package com.shopweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopweb.entity.Product;
import com.shopweb.repository.ProductRepository;
import com.shopweb.service.ProductService;
import com.shopweb.utils.PageBean;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductRepository productRepository;
	

	public List<Product> findHot() {
		return productRepository.findHot();
	}

	public List<Product> findNew() {
		return productRepository.findNew();
	}

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return productRepository.findById(pid);
	}

	// 根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		totalCount = productRepository.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productRepository.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据二级分类的csid带有分页查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		totalCount = productRepository.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;

		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productRepository.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//后台分页查询商品
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productRepository.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;

		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productRepository.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productRepository.save(product);
		
	}

	public void delete(Product product) {
		productRepository.delete(product);
		
	}

	public void update(Product product) {
		productRepository.update(product);
		
	}
	
}
