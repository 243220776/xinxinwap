package com.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.app.domain.User;
import com.app.mapper.UserMapper;
import com.app.service.UserService;
import com.app.utils.AppContext;
import com.app.utils.QQAPI;
import com.google.common.base.Strings;

@RestController
@RequestMapping("qq")
public class QQLogin {
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping("/login")
	@ResponseBody
	public String Login(String code){
		JSONObject json = new JSONObject();
		
		JSONObject qqData = QQAPI.authQQ(code);
		System.out.println(qqData);
		String openId = qqData.getString("openId");//唯一ID
		String nickname = qqData.getString("nickname");//qq空间昵称
//		String gender = qqData.getString("gender");//性别
		String figureurl_1 = qqData.getString("figureurl_1");//头像50x50
		String pwd = "123456";
		int count = userService.findOpenIdCount(openId);
		if(count == 0){
			Integer findByUID = userMapper.getUID();
			User user = new User();
			user.setOpenIdQQ(openId);
			user.setUsername(nickname);
			user.setPassword(pwd);
			user.setuID(findByUID+1);
			user.setReg_time(new Date());
			user.setHeadImg(figureurl_1);
			Integer userInfo = userMapper.addUser(user);
			if(userInfo == 1){//成功
				authLogin(openId,pwd);//调用授权方法
			}else{
				json.put("code", 1004);
				json.put("msg", "用户信息写入失败，请稍后再试！");
				return json.toString();
			}
		}else if(count == 1){
			authLogin(openId,pwd);//调用授权方法
		}
		return null;
	}
	
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

}
