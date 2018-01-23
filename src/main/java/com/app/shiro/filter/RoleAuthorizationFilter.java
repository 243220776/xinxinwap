package com.app.shiro.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import org.apache.shiro.web.util.WebUtils;

public class RoleAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
//		return super.onAccessDenied(request, response, mappedValue);
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
        
        Subject subject = getSubject(httpRequest, httpResponse);
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(subject.getPrincipal() == null) {
        	if(com.app.utils.WebUtils.isAjax(httpRequest)){
        		map.put("success", false);
        		map.put("message", "您尚未登录或登录时间过长,请重新登录!");
        		map.put("errorCode", 101);
        		com.app.utils.WebUtils.sendJson(httpResponse, map);
        	} else {
        		super.saveRequestAndRedirectToLogin(httpRequest, httpResponse);
        	}
        } else {
        	if(com.app.utils.WebUtils.isAjax(httpRequest)){
        		map.put("success", false);
        		map.put("message", "您没有足够的权限执行该操作!");
        		map.put("errorCode", 102);
        		com.app.utils.WebUtils.sendJson(httpResponse, map);
        	} else {
        		String unauthorizedUrl = getUnauthorizedUrl();  
                if (StringUtils.hasText(unauthorizedUrl)) {  
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);  
                } else {  
                    WebUtils.toHttp(response).sendError(401);  
                } 
        	}
        	 
        }
        
        return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);  
        String[] rolesArray = (String[]) mappedValue;  
        
        if (rolesArray == null || rolesArray.length == 0) {  
            // no roles specified, so nothing to check - allow access.  
            return true;  
        }  
  
        Set<String> roles = CollectionUtils.asSet(rolesArray);  
        for (String role : roles) {  
            if (subject.hasRole(role)) {  
                return true;  
            }  
        }  
        return false;  
	}
	
}
