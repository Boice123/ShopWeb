package com.shopweb.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;
import com.shopweb.repository.OrderRepository;
import com.shopweb.utils.PageHibernateCallback;
@Repository("orderRepository")
public class OrderRepositoryImpl extends BaseDaoImpl<Order> implements
		OrderRepository {

	// 查询我的订单分页查询:统计个数
	public List<Order> findPageById(Integer id, int begin, int limit) {
		String hql = "from Order o where o.user.id = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { id },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 查询我的订单分页查询:查询数据
	public Integer findCountById(Integer id) {
		String hql = "select count(*) from Order o where o.user.id = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, id);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 统计订单个数的方法
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页查询订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}
	
	// 根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	
}
