package com.shopweb.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shopweb.base.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	//T  ����ʱ��ע�ͱ�����������ʱ���ſ��Ի�þ��������
	private Class<?> beanClass;
	public BaseDaoImpl() {
		//�������ʱ������ , BaseDaoImpl<CrmStaff>��������������
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//���ʵ�ʲ����� ,������С�
		beanClass = (Class<?>) paramType.getActualTypeArguments()[0];
	}

	@Resource  
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    } 
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}


	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}


	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(beanClass, id);
	}


	public List<T> findAll() {
		return this.getHibernateTemplate().find("from " + beanClass.getName());
		

		
	}


}
