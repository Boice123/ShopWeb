package com.shopweb.repository;

import java.util.List;

import com.shopweb.base.BaseDao;
import com.shopweb.entity.CategorySecond;

public interface CategorySecondRepository extends BaseDao<CategorySecond>{

	public int findCount();

	public List<CategorySecond> findByPage(int begin, int limit);

}
