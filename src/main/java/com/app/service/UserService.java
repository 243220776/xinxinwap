package com.app.service;

import com.app.domain.User;

public interface UserService {
	
	public String getUser();//获取用户信息
	
	public User getByNameOrPhone(String loginName);//查询登录信息
	
	public Integer phoneExist(String phone);//手机是否存在
	
	public Integer userExist(String user_name);//用户名是否存在
	
	public String register(User user,Integer code);//注册
	
	public String updatePwd(String phone,String password,Integer code);//修改密码

	public int findOpenIdCount(String openId);//查询qq唯一ID

	
}
