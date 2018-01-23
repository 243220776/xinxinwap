package com.app.service;

import com.app.domain.User;

public interface UserService {
	
	public String getUser();
	
	public User getByNameOrPhone(String loginName);
	
	public Integer phoneExist(String phone);
	
	public Integer userExist(String user_name);
	
	public String register(User user,Integer code);
	
	public String updatePwd(String phone,String password,Integer code);

	
}
