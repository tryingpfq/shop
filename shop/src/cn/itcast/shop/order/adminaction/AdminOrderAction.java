package cn.itcast.shop.order.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {
    
	//注入service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//接收页面传来的page;
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//接收oid 用来查询订单项
	private Integer oid;
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	//模型驱动
	private Order order=new Order();
	public Order getModel() {
		return order;
	}
	//查找所有的订单
	public String findAll(){
	
		PageBean pageBean=orderService.findByPage(page);
		//把pageBean放到值栈当中去
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//订单详情，查询订单项
	public String findOrderItem(){
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	//修改订单状态
	public String updateState(){
		//查询订单
		Order currOrder =orderService.findByOid(order.getOid());
		//修改订单
		currOrder.setState(3);
		//更新订单
		orderService.update(currOrder);
		return "update";
	}

}
