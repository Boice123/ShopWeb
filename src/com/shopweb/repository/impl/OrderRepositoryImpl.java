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

	// ��ѯ�ҵĶ�����ҳ��ѯ:ͳ�Ƹ���
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

	// ��ѯ�ҵĶ�����ҳ��ѯ:��ѯ����
	public Integer findCountById(Integer id) {
		String hql = "select count(*) from Order o where o.user.id = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, id);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ͳ�ƶ��������ķ���
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ��ҳ��ѯ�����ķ���
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}
	
	// ���ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	
}
