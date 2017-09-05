package com.shopweb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 购物车对象
 * 
 * 
 * 
 */
public class Cart implements Serializable{

	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	// 购物总计:
	private double total;

	public double getTotal() {
		return total;
	}

	// 购物车的功能:
	// 1.将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		
		Integer pid = cartItem.getProduct().getPid();
		// 判断购物车中是否已经存在该购物项:
		if(map.containsKey(pid)){
			// 存在
			CartItem c = map.get(pid);// 获得购物车中原来的购物项
			c.setCount(c.getCount()+cartItem.getCount());
		}else{
			// 不存在
			map.put(pid, cartItem);
		}
		// 设置总计的值
		total += cartItem.getSubtotal();
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计 -移除的购物项小计:
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}
}
