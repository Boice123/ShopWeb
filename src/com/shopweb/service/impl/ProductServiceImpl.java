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

	// ������ƷID��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		return productRepository.findById(pid);
	}

	// ����һ�������cid���з�ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 8;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = productRepository.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Product> list = productRepository.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//���ݶ��������csid���з�ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 8;
		pageBean.setLimit(limit);
		//  �����ܼ�¼��:
		int totalCount = 0 ;
		totalCount = productRepository.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;

		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
		int begin = (page - 1) * limit;
		List<Product> list = productRepository.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//��̨��ҳ��ѯ��Ʒ
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = productRepository.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��:
		int totalPage = 0;

		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�����ݼ���:
		// ���Ŀ�ʼ:
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
