package cn.itcast.shop.categorysecond.service;

import java.util.List;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

/**
 * 业务层管理
 * @author Administrator
 *
 */
public class CategorySecondService {
	//注入dao
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	//查找所有二级分类
	public PageBean<CategorySecond> findByPage(Integer page) {
		//创建一个pagebean 
		PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
	    pageBean.setPage(page);
		//设置每页的记录数
		Integer limit=10;
		pageBean.setLimit(limit);
		//设置总的记录数
		Integer totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总的页数
		Integer totalPage=0;
		if(totalCount % limit==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//添加二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}
	//根据csid查找二级分类
	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
		
	}
	//查询所有二级分类的方法
	public List<CategorySecond> findAll() {
	
		return categorySecondDao.findAll();
	}
	
}
