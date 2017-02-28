package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class OrderService {
	// 注入OrderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 业务层保存订单的方法
	public void save(Order order) {
		orderDao.save(order);
	}

	//我的订单的业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前的页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit=5;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount=orderDao.findByConutUid(uid);
		//设置总的页数
		Integer totalPage=0;
		if(totalCount % limit ==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit +1;
		}
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(totalCount);
		Integer begin=(page-1) * limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
    
	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		Order order=orderDao.findByOid(oid);
		return order;
	}

	//业务层修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}

	//分页查询订单的方法
	public PageBean findByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前的页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount=orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		Integer totalPage=0;
		if(totalCount % limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页放回的记录数
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
   
	//后台根据oid查询订单的所有订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		
		return orderDao.findOrderItem(oid);
	}
}
