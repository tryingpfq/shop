package cn.itcast.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;

/**
 * 后台登录的业务层类
 * @author Administrator
 *
 */
@Transactional
public class AdminUserService {
    //注入dao
    private AdminUserDao adminUserDao;
    public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
    
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}
    
}
