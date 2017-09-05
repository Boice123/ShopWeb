package com.shopweb.service;

import java.util.List;

import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;
import com.shopweb.utils.PageBean;

public interface OrderService {

	public void save(Order order);

	public PageBean<Order> findByPageid(Integer id, Integer page);

	public Order findByOid(Integer oid);

	public void update(Order order);

	public PageBean<Order> findAll(Integer page);

	public List<OrderItem> findOrderItem(Integer oid);





}
