package com.shopweb.repository;

import java.util.List;

import com.shopweb.base.BaseDao;
import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;

public interface OrderRepository extends BaseDao<Order> {

	public List<Order> findPageById(Integer id, int begin, int limit);

	public Integer findCountById(Integer id);

	public int findCount();

	public List<Order> findByPage(int begin, int limit);

	public List<OrderItem> findOrderItem(Integer oid);


}
