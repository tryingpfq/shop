package cn.itcast.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;

/**
 * 后台一级分类的action
 * @author Administrator
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category> {

	//模型驱动
	private Category category=new Category();
	public Category getModel() {
		return category;
	}
     //注入service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/*//接收cid
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}*/
	//查询所有一级分类的方法
	public String findAll(){
      //查询所有一级分类
		List<Category> cList=categoryService.findAll();
		//将集合的数据显示在页面当中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//添加一级分类
	public String save(){
		System.out.println(category.getCname());
		categoryService.save(category);
		return "saveSuccess";
	}
	//删除一级分类
	public String delete(){
		//调用service完成一级分类的删除 一定要先查询再
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "delete";
	}
	//跳转到编辑页面
	public String edit(){
		//先根据cid进行查找
		category=categoryService.findByCid(category.getCid());
		return "edit";
	}
	//修改一级分类
	public String update(){
		categoryService.update(category);
		return "editSuccess";
	}
}
