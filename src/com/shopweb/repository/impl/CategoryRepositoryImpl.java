package com.shopweb.repository.impl;

import org.springframework.stereotype.Repository;
import com.shopweb.base.impl.BaseDaoImpl;
import com.shopweb.entity.Category;
import com.shopweb.repository.CategoryRepository;

@Repository("categoryRepository") 
public class CategoryRepositoryImpl extends BaseDaoImpl<Category> implements CategoryRepository {

}
