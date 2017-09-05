package com.shopweb.repository;

import java.util.List;

import com.shopweb.base.BaseDao;
import com.shopweb.entity.Product;

public interface ProductRepository extends BaseDao<Product> {

	public List<Product> findHot();

	public List<Product> findNew();

	public int findCountCid(Integer cid);

	public List<Product> findByPageCid(Integer cid, int begin, int limit);

	public int findCountCsid(Integer csid);

	public List<Product> findByPageCsid(Integer csid, int begin, int limit);

	public List<Product> findByPage(int begin, int limit);

	public int findCount();

}
