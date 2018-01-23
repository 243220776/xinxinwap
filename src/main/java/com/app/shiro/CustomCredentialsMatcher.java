package com.app.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;


//import com.yanchengtech.back.ui.core.utils.EndecryptUtils;


/**
 * @author xiaofengzhang
 * @version 1.0
 */
public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
	
	private Cache<String, AtomicInteger> passwordRetryCache;

    public CustomCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }


    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
    	UsernamePasswordToken authToken = (UsernamePasswordToken)token;
    	
		String username = (String)authToken.getUsername();
		
		//retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {//连续五次密码错误
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }
        
        
        //数据库如果是md5加密后的密码，可用这个
//		Object tokenCredentials = EndecryptUtils.encrypt(String.valueOf(authToken.getPassword()));
        Object tokenCredentials = authToken.getPassword();
		Object accountCredentials = info.getCredentials();
				
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		boolean matches = equals(tokenCredentials, accountCredentials);
		if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
		return matches;
	}

}
