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
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderRepository.findCountById(id);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
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
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = orderRepository.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderRepository.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层查询订单项的方法
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderRepository.findOrderItem(oid);
	}


	
}
