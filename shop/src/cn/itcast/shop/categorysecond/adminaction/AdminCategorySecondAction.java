package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageBean;

/**
 * 后台二级分类的action
 * @author Administrator
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {
       //注入service
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//模型驱动
	private CategorySecond categorySecond =new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//查询所有的二级分类
	public String findAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		//将pageBean的数据保存在值栈当中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//跳转到一级分类
	public String addPage(){
		List<Category> cList=categoryService.findAll();
		//把list放入到值栈当中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//保存添加的二级分类
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//删除二级分类
	public String delete(){
		//本来是要先查找 然后再级联删除  这里就不做了 因为商品都是导入好了的
		//categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "delete";
	}
	//编辑二级分类的方法
	public String edit(){
		//根据二级分类id查询二级分类的对象
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有的一级分类
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//修改二级分类
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
	
}
