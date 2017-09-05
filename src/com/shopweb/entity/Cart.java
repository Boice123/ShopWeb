package com.shopweb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * ���ﳵ����
 * 
 * 
 * 
 */
public class Cart implements Serializable{

	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();

	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	// �����ܼ�:
	private double total;

	public double getTotal() {
		return total;
	}

	// ���ﳵ�Ĺ���:
	// 1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem) {
		
		Integer pid = cartItem.getProduct().getPid();
		// �жϹ��ﳵ���Ƿ��Ѿ����ڸù�����:
		if(map.containsKey(pid)){
			// ����
			CartItem c = map.get(pid);// ��ù��ﳵ��ԭ���Ĺ�����
			c.setCount(c.getCount()+cartItem.getCount());
		}else{
			// ������
			map.put(pid, cartItem);
		}
		// �����ܼƵ�ֵ
		total += cartItem.getSubtotal();
	}

	// 2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid) {
		// ���������Ƴ����ﳵ:
		CartItem cartItem = map.remove(pid);
		// �ܼ� = �ܼ� -�Ƴ��Ĺ�����С��:
		total -= cartItem.getSubtotal();
	}

	// 3.��չ��ﳵ
	public void clearCart() {
		// �����й��������
		map.clear();
		// ���ܼ�����Ϊ0
		total = 0;
	}
}
