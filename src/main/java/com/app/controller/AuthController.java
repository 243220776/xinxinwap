package com.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.app.domain.User;
import com.app.service.UserService;
import com.app.utils.AppContext;
import com.google.common.base.Strings;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	UserService userService;

	/**
	 * 用户登录功能
	 * @param loginName
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public Map<String, Object> authLogin(String loginName,String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (Strings.isNullOrEmpty(loginName)) {
			map.put("msg", "用户名不能为空");
			return map;
		}

		if (Strings.isNullOrEmpty(pwd)) {
			map.put("msg", "密码不能为空");
			return map;
		}

		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);
		try {
			subject.login(token);
			/*if (WebUtils.getSavedRequest(request)!=null) {
				 获取跳转至登录页面的起始页面url   哪个页面被拦截就是获取哪一页
				String requestUrl = WebUtils.getSavedRequest(request).getRequestUrl();
				System.out.println("=======================<"+requestUrl);
				if (StringUtils.isNotBlank(requestUrl)) {
					map.put("redirect", requestUrl);
				}
			}else{
				map.put("redirect", "/tryUse/tryManage");
			}*/
		} catch (UnknownAccountException e) {
			System.out.println("UnknownAccountException 输入的信息：用户名 "+loginName+",密码"+pwd);
			map.put("msg", "账号不存在！");
			return map;
		} catch (IncorrectCredentialsException e) {//数据库需要MD5加密后的密码
			System.out.println("IncorrectCredentialsException 输入的信息：用户名 "+loginName+",密码"+pwd);
			map.put("msg", "密码错误！");
			return map;
		} catch (ExcessiveAttemptsException e) {
			System.out.println("ExcessiveAttemptsException 输入的信息：用户名 "+loginName+",密码"+pwd);
			map.put("msg", "登陆失败5次，请一小时后再试");
			return map;
		} catch (LockedAccountException e){
			System.out.println("LockedAccountException 输入的信息：用户名 "+loginName+",密码"+pwd);
			map.put("msg", "此账号已被锁定！");
			return map;
		} catch (AuthenticationException e) {
			System.out.println("AuthenticationException 输入的信息：用户名 "+loginName+",密码"+pwd);
			map.put("msg", "无效用户名密码");
			return map;
		}

		User findUserByName = userService.getByNameOrPhone(loginName);
		if (null != findUserByName) {
			AppContext.setCurrentUser(findUserByName);
			map.put("msg", "success");
			map.put("code", "0");
		}

		return map;
	}
	
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(User user,Integer code,HttpServletRequest request){
		
		return userService.register(user,code);
	}
	
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	public String updatePwd(String phone,String password,Integer code){

		return userService.updatePwd(phone,password,code);

	}
	
	
	
	@RequestMapping("logout")
	public String logout() {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("msg", "您已安全退出！");
		return json.toString();
	}
	
	//是否登录
	@RequestMapping(value = "isLogined", method = RequestMethod.POST)
	public String isLogined(){
		if (AppContext.getCurrentUser()!=null) {
			return "true";
		}
		return "false";
	}
	
	//用户名
	@RequestMapping(value = "getUserName", method = RequestMethod.POST)
	public String getUserName(){
		if(AppContext.getCurrentUser()!=null){
			return AppContext.getCurrentUser().getUsername();
		}else{
			return null;
		}
	}
	
	//手机号是否存在
	@RequestMapping(value="phoneExist",method=RequestMethod.POST)
	public Integer phoneExist(String phone){
		
		return userService.phoneExist(phone);
	}
	
	//昵称是否存在
	@RequestMapping(value="userExist",method=RequestMethod.POST)
	public Integer userExist(String user_name){
		
		return userService.userExist(user_name);
	}
		
}