package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

/**
 * 一级分类的持久层对象
 * @author 传智.郭嘉
 *
 */
public class CategoryDao extends HibernateDaoSupport{

	//DAO层的查询所有一级分类的方法
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	// Dao中的保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
		
	}
    //根据cid查询一级分类
	public Category findByCid(Integer cid) {
		String hql="from Category c where c.cid=? ";
		List<Category> list=this.getHibernateTemplate().find(hql, cid);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
    //修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
		
	}

	
}
