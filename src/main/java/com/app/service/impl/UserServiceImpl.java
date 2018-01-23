package com.app.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.app.domain.User;
import com.app.mapper.UserMapper;
import com.app.service.UserService;
import com.app.utils.AppContext;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	TelCodeserviceImpl telCodeserviceImpl;
	
	@Override
	public String getUser() {
		JSONObject ret = new JSONObject();
		
		User user = userMapper.getUser(AppContext.getCurrentUserId());
		if(user!=null){
			ret.put("code", 0);
			ret.put("msg", "success");
			ret.put("data", user);
		}else{
			ret.put("code", 1001);
			ret.put("msg", "fail");
		}
		return ret.toString();
	}

	@Override
	public User getByNameOrPhone(String loginName) {
		// TODO Auto-generated method stub
		return userMapper.getByNameOrPhone(loginName);
	}

	@Override
	public Integer phoneExist(String phone) {
		User findByPhone = userMapper.phoneExist(phone);
		if(findByPhone!=null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public Integer userExist(String user_name) {
		User findByName = userMapper.userExist(user_name);
		if(findByName!=null){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public String register(User user, Integer code) {
		JSONObject json = new JSONObject();
		
		User findByName = userMapper.userExist(user.getUsername());
		User findByPhone = userMapper.phoneExist(user.getPhone());
		Integer findByUID = userMapper.getUID();
				
		if(findByName!=null){
			json.put("code", 1001);
			json.put("msg", "昵称已被占用！");
			return json.toString();
		}
		if(findByPhone!=null){
			json.put("code", 1002);
			json.put("msg", "手机号已被注册！");
			return json.toString();
		}
		
		String validateCode = telCodeserviceImpl.validateCode(user.getPhone(),code);
		JSONObject parseObject = JSONObject.parseObject(validateCode);
		if (parseObject.getInteger("code") == 1) {  //验证码通过
			user.setReg_time(new Date());
			user.setUsername(user.getUsername());
			user.setPhone(user.getPhone());
			user.setPassword(user.getPassword());
			user.setuID(findByUID+1);//获取最大的加1
			
//			try {
//				user.setPassword(MD5.convert(user.getPassword()));
//			} catch (Exception e) {
//				e.printStackTrace();
//				json.put("code", 1003);
//				json.put("msg", "网络连接超时，请稍后再试！");
//			}
			Integer userInfo = userMapper.addUser(user);
			if(userInfo==1){
				json.put("code", 1);
				json.put("msg", "success");
				return json.toString();
			}else{
				json.put("code", 1004);
				json.put("msg", "注册失败，现在注册用户过多，请稍等！");
				return json.toString();
			}
		}
		return validateCode;
	}

	@Override
	public String updatePwd(String phone, String password, Integer code) {
		JSONObject json = new JSONObject();
		User user = userMapper.getUerIdAndPhone(phone);
		if(user==null){
			json.put("code", 1001);
	        json.put("msg", "对不起，此手机号未被注册过！");
	        return json.toString();
		}
		String validateCode = telCodeserviceImpl.validateCode(user.getPhone(),code);
		JSONObject parseObject = JSONObject.parseObject(validateCode);
		
		if (parseObject.getInteger("code") == 1) {//验证码通过
			user.setPassword(password);
			Integer updatePwd = userMapper.updateUser(user);
			
//			try {
//				user.setPassword(MD5.convert(password));
//				Integer updatePwd = userMapper.updateUser(user);
//			} catch (Exception e) {
//				e.printStackTrace();
//				json.put("code", 1003);
//				json.put("msg", "网络连接超时，请稍后再试！");
//			}
			
			if(updatePwd==1){
				json.put("code", 1);
				json.put("msg", "success");
				return json.toString();
			}else{
				json.put("code", 1002);
				json.put("msg", "网络连接超时，请稍后再试！");
				return json.toString();
			}
		}  
		
		return validateCode;
	}

}
