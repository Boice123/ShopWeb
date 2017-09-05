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
		
		// ��session�ܻ�ù��ﳵ��Ϣ.
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			model.addAttribute("ordererror", "��!���ﳵ���ǿյ���!");
			return "msg";
		}
		Order order = new Order();
		order.setTotal(cart.getTotal());
		// ���ö�����״̬
		order.setState(1); 
		// ���ö���ʱ��
		order.setOrdertime(new Date());
		// ���ö��������Ŀͻ�:
		User existUser = (User) request.getSession().getAttribute("existUser");
		if (existUser == null) {
			model.addAttribute("loginerror","����û�е�¼!");
			return "login";
		}
		order.setUser(existUser);
		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
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
		// 2.��ɸ���:
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://192.168.1.106:8080/ShopWeb/order/callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		//String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
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
		
		// �ض���:���ױ�����:
		response.sendRedirect(sb.toString());
		
	}
	
	// ����ɹ�����ת������·��:
	@RequestMapping("/callBack")
	public String callBack(String r6_Order,String r3_Amt,Model model){
		// �޸Ķ�����״̬:
		Order nowOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// �޸Ķ���״̬Ϊ2:�Ѿ�����:
		nowOrder.setState(2);
		orderService.update(nowOrder);
		model.addAttribute("paysuccess", "֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		return "msg";
	}
	
	// �޸Ķ�����״̬:
	@RequestMapping("/updateState")
	public String updateState(Order order){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "redirect:/order/findOrderById?page=1";
	}
}