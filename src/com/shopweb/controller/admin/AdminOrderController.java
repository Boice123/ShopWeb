package com.shopweb.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;
import com.shopweb.service.OrderService;
import com.shopweb.utils.PageBean;

@Controller("adminOrderController")
@RequestMapping("/admin/adminOrder")
public class AdminOrderController {
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/findAll")
	public String findAll(Integer page,Model model){
		PageBean<Order> pageBean = orderService.findAll(page);
		model.addAttribute("pageBean", pageBean);
		return "../admin/order/list";
	}
	
	// ���ݶ�����id��ѯ������:
	@RequestMapping("/findOrderItem")
	public String findOrderItem(Order order,Model model){
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		model.addAttribute("list", list);
		return "../admin/order/orderItem";
	}
	
	// �޸Ķ���״̬
	@RequestMapping("/updateState")
	public String updateState(Order order){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "redirect:/admin/adminOrder/findAll?page=1";
	}
}
