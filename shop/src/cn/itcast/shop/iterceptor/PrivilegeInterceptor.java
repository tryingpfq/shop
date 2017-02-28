package cn.itcast.shop.iterceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * 判断后台用户是否登录
 * @author Administrator
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {



	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断是否登录 如果登录 放行  没有登录 转到登录页面
		AdminUser adminUser=(AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(adminUser!=null){
			//已近通过
			return actionInvocation.invoke();
		}else{
			// 跳转到登录页面:
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没有登录!没有权限访问!");
			return "loginFail";
		}
		
	}

}
