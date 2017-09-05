package com.shopweb.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopweb.entity.Cart;
import com.shopweb.entity.CartItem;
import com.shopweb.entity.Order;
import com.shopweb.entity.OrderItem;
import com.shopweb.entity.User;
import com.shopweb.service.OrderService;
import com.shopweb.utils.PageBean;
import com.shopweb.utils.PaymentUtil;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Resource
	private OrderService orderService;
	
	@Resource
	private HttpServletRequest request;
	
	
	@RequestMapping("/orderUI")
	public String saveOrder(Model model){
		
		// 从session总获得购物车信息.
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			model.addAttribute("ordererror", "亲!购物车还是空的呢!");
			return "msg";
		}
		Order order = new Order();
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		order.setState(1); 
		// 设置订单时间
		order.setOrdertime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) request.getSession().getAttribute("existUser");
		if (existUser == null) {
			model.addAttribute("loginerror","您还没有登录!");
			return "login";
		}
		order.setUser(existUser);
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// 清空购物车:
		cart.clearCart();

		model.addAttribute("orderInfo",order);
		return "order/order";
	}
	
	@RequestMapping("/findOrderById")
	public String findOrderById(Integer page,Model model){
		User user = (User) request.getSession().getAttribute("existUser");
		PageBean<Order> pagebean = orderService.findByPageid(user.getId(),page);
		model.addAttribute("pagebean", pagebean);
		return "order/orderList";
	}
	
	@RequestMapping("/findByOid")
	public String findByOid(Integer oid,Model model){
		Order order = orderService.findByOid(oid);
		model.addAttribute("orderInfo", order);
		return "order/order";
	}
	
	@RequestMapping("/payOrder")
	public void payOrder(Order order,String pd_FrpId,HttpServletResponse response) throws Exception{
		Order nowOrder = orderService.findByOid(order.getOid());
		nowOrder.setAddr(order.getAddr());
		nowOrder.setName(order.getName());
		nowOrder.setPhone(order.getPhone());
		orderService.update(nowOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://192.168.1.106:8080/ShopWeb/order/callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		//String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		response.sendRedirect(sb.toString());
		
	}
	
	// 付款成功后跳转回来的路径:
	@RequestMapping("/callBack")
	public String callBack(String r6_Order,String r3_Amt,Model model){
		// 修改订单的状态:
		Order nowOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		nowOrder.setState(2);
		orderService.update(nowOrder);
		model.addAttribute("paysuccess", "支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	// 修改订单的状态:
	@RequestMapping("/updateState")
	public String updateState(Order order){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "redirect:/order/findOrderById?page=1";
	}
}