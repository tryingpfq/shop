package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * 一级分类的业务层对象
 * @author 传智.郭嘉
 *
 */
@Transactional
public class CategoryService {
	// 注入CategoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	// 业务层查询所有一级分类的方法
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	// 业务层保存一级分类的操作
	public void save(Category category) {
		categoryDao.save(category);
	}

	public void delete(Category category) {
	
		categoryDao.delete(category);
		
	}
    //根据cid查询一级分类
    public Category findByCid(Integer cid) {
		
		return categoryDao.findByCid(cid);
	}
    //修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
		
	}

}
