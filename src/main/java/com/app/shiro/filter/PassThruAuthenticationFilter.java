package com.app.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PassThruAuthenticationFilter extends org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter {
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }
	
}
