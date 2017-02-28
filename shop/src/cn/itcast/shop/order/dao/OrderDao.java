package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;
import freemarker.template.utility.Execute;

public class OrderDao extends HibernateDaoSupport {

	// Dao层的保存订单额操作
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
  
	//我的订单的总的记录是
	public Integer findByConutUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=? ";
		List<Long> list=this.getHibernateTemplate().find(hql, uid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}

	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid=? order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		String hql="from Order o where o.oid=? ";
		List<Order> list=this.getHibernateTemplate().find(hql, oid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
    //Dao层修改订单
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
		
	}
    //设置总的记录数
	public Integer findCount() {
		String hql="select count(*) from Order ";
		List<Long> count=this.getHibernateTemplate().find(hql);
		if(count!=null && count.size()>0){
			return count.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findByPage(Integer begin, Integer limit) {
		String hql="from Order o order by o.ordertime desc ";
		List<Order> list=(List<Order>) this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
     
	//根据oid 查询订单的所有订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid= ? ";
		List<OrderItem> list=this.getHibernateTemplate().find(hql, oid);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
