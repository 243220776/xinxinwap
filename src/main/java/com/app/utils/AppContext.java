package com.app.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.app.domain.User;


@Component
public class AppContext {
	
	public static void setCurrentUser(User user) {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute("userInfo", user);
	}
	
	public static User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		return (User) subject.getSession().getAttribute("userInfo");
	}
	
	public static Integer getCurrentUserId() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getId();
		}
		return 0;
	}
}
