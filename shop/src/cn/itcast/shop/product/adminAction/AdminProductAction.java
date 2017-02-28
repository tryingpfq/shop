package cn.itcast.shop.product.adminAction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	//注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//注入二级分类的service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//Struts2 上传文件需要的参数
	private File upload;  //要和表单中的name一致
	private String uploadFileName;   //文件的名字
	private String uploadContenType;  //文件类型
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContenType(String uploadContenType) {
		this.uploadContenType = uploadContenType;
	}
	//接收页面传过来的page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//模型驱动
	private Product product =new Product();
	public Product getModel() {
		
		return product;
	}
	//查找所有的商品
	public String findAll(){
		PageBean pageBean=productService.findByPage(page);
		//把pageBean放入到值栈当中去
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//删除商品
	public String delete(){
		System.out.println(product.getPdesc());
		productService.delete(product);
		return "delete";
	}
	//添加页面
	public String addPage(){
		//查询二级分类的集合
		List<CategorySecond> csList=categorySecondService.findAll();
		//放入到值栈当中去
		ActionContext.getContext().getValueStack().set("csList", csList);
		//进行页面的跳转
		return "addPage";
	}
	//保存商品
	public String save() throws IOException{
		//调用service保存
		product.setPdate(new Date());
		if(upload!=null){
			//将商品图片上传到服务器上
			//获得上传图片的服务器端的路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
			//创建文件类型对象
			File diskFile = new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			//把相对路径保存到image中
			product.setImage("products/"+uploadFileName);
			
		}
		productService.save(product);
		return "addSuccess";
	}
	//修改商品信息
	public String edit(){
		//首先进行查询
		product =productService.findByPid(product.getPid());
		//然后把所有的二级分类查询出来
		List<CategorySecond> csList=categorySecondService.findAll();
		//把list放入到值栈当中去
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	//修改后对商品进行保存
	public String update() throws IOException{
		//对时间进行更新
		product.setPdate(new Date());
		//判断商品的图片是否有所改变
		if(upload!=null){
			//将商品图片上传到服务器，并获得路径
			String realPath=ServletActionContext.getServletContext().getRealPath("/products");
		    //创建文件类型对象
			File disKFile=new File(realPath+"//"+uploadFileName);
			//上传文件
			FileUtil.copyFile(upload, disKFile);
			product.setImage("products/"+uploadFileName);
		}
		//调用service进行更新
		productService.update(product);
		return "update";
	}

}
