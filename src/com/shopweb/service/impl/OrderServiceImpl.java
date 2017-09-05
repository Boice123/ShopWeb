package com.shopweb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;
import com.shopweb.repository.OrderRepository;
import com.shopweb.service.OrderService;
import com.shopweb.utils.PageBean;

@Service
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderRepository orderRepository; 

	public void save(Order order) {
		this.orderRepository.save(order);
		
	}

	public PageBean<Order> findByPageid(Integer id, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = orderRepository.findCountById(id);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���:
		int begin = (page - 1)*limit;
		List<Order> list = orderRepository.findPageById(id,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		return orderRepository.findById(oid);
	}

	public void update(Order order) {
		orderRepository.update(order);
		
	}

	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���ò���
		pageBean.setPage(page);
		// ����ÿҳ��ʾ�ļ�¼��:
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��
		int totalCount = orderRepository.findCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���
		int begin = (page - 1) * limit;
		List<Order> list = orderRepository.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ҵ����ѯ������ķ���
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderRepository.findOrderItem(oid);
	}


	
}
