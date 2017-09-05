package com.shopweb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Cart;
import com.shopweb.entity.CartItem;
import com.shopweb.entity.Product;
import com.shopweb.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private  HttpServletRequest request;
	
	@Resource
	private ProductService productService;
	
	@RequestMapping("/addCart")
	public String addCart(Integer pid,Integer count,CartItem cartItem,HttpSession session){
		//从session中取出购物车
		Cart cart = getCart();
		Product p = productService.findByPid(pid);
		cartItem.setProduct(p);
		cartItem.setCount(count);
		cart.addCart(cartItem);
		
		return "cart/cart";
	}
	
	@RequestMapping("/clearCart")
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "cart/cart";
	}
	
	@RequestMapping("/removeCart")
	public String removeCart(Integer pid){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "cart/cart";
	}
	
	@RequestMapping("/myCart")
	public String myCart(){
		return "cart/cart";
	}
	
	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * @return
	 */
	private Cart getCart() {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
					session.setAttribute("cart", cart);
		}
		return cart;
	}
	
}
