package com.shopweb.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shopweb.base.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	//T  编译时，注释变量。在运行时，才可以获得具体的类型
	private Class<?> beanClass;
	public BaseDaoImpl() {
		//获得运行时的类型 , BaseDaoImpl<CrmStaff>被参数化的类型
		ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得实际参数的 ,获得所有。
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
