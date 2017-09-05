package com.shopweb.repository.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.Product;
import com.shopweb.repository.ProductRepository;
import com.shopweb.utils.PageHibernateCallback;
@Repository("productRepository") 
public class ProductRepositoryImpl extends BaseDaoImpl<Product> implements
		ProductRepository {

	// ��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		// ʹ������������ѯ.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// ��ѯ���ŵ���Ʒ
		criteria.add(Restrictions.eq("is_hot", 1));
		// �����������:
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ:
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	// ��ҳ��������Ʒ��ѯ
	public List<Product> findNew() {
		// ʹ������������ѯ.
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// �����������:
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ:
		List<Product> list = this.getHibernateTemplate().findByCriteria(
				criteria, 0, 10);
		return list;
	}

	// ����һ������cid��ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	// ����һ������cid��ѯ��Ʒ�ļ���
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql = "Select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{cid},begin,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	// ���ݶ�������csid��ѯ��Ʒ�ĸ���
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݶ�������csid��ѯ��Ʒ�ļ���
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "Select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{csid},begin,limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	// ��̨ͳ����Ʒ�����ķ���
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	// ��̨��ѯ������Ʒ�ķ���
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	

}
