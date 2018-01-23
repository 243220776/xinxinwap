package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制类 -- 用于指定显示的页面
 * @author 二更
 *
 */
@Controller
public class PageController {
	
	//前往首页
	@RequestMapping(value = {"/","index"})
	public String  indexPage(){
		
		return "/html/index.html";
	}
	
	//登录页面	
	@RequestMapping("/auth/goLogin")
	public String goLogin(){
		return "/html/login.html";
	}
	
	//个人中心页面
	@RequestMapping("/user/goUserInfo")
	public String goUserInfo(){
		return "/html/user.html";
	}
	
	//注册
//	@RequestMapping("/auth/goRegister")
//	public String goRegister(){
//		return "/html/userLogin/register.html";
//	}
//	
//	@RequestMapping("/auth/goForgetPwd")
//	public String goForgetPwd(){
//		return "/html/userLogin/forgetPwd.html";
//	}
	
}
