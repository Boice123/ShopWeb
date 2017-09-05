package com.shopweb.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.CategorySecond;
import com.shopweb.repository.CategorySecondRepository;
import com.shopweb.utils.PageHibernateCallback;

@Repository("categorySecondRepository")
public class CategorySecondRepositoryImpl extends BaseDaoImpl<CategorySecond>
		implements CategorySecondRepository {

	// 统计二级分类个数的方法
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页查询的方法
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		return list;
	}
	

}
