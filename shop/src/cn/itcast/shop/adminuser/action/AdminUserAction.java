package cn.itcast.shop.adminuser.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	//模型驱动
    private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
    //注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
    
	//后台登录
	public String login(){
		//调用service
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			//登录失败
			this.addActionError("亲，你的用户名或者密码错误");
			return "loginFail";
		}else{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	//管理员退出登录
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
}
