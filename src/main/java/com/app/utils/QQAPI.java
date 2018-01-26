package com.app.utils;

import com.alibaba.fastjson.JSONObject;
import com.app.utils.HttpRequest;

public class QQAPI {
	
	private final static String APP_ID = "101454904";
	private final static String APP_KEY = "e49ef2c34522964a2aba1427738eccbb";
	private final static String RED_IRECT_URI = "http://xinxin.link";
	
	public static JSONObject authQQ(String code){
		String str = code.split("&")[0];
		String codes = str.split("=")[1];
		System.out.println("code:"+codes);
		
		String token = getAccessToken(codes);
		System.out.println("AccessToken:"+token);
		
		String openId = getOpenId(token);
		System.out.println("openId:"+openId);

		String userInfo = getUserInfo(token,openId);
		System.out.println("userInfo:"+userInfo);
		JSONObject json = JSONObject.parseObject(userInfo);
		json.put("openId", openId);
		
		return json;
	}
	
	public static String getAccessToken(String codes){
		String grant_type = "grant_type=authorization_code";
		String client_id = "client_id="+APP_ID;
		String client_secret = "client_secret="+APP_KEY;
		String code = "code="+codes;
		String redirect_uri = "redirect_uri="+RED_IRECT_URI;
		
		String url = "https://graph.qq.com/oauth2.0/token";//pc
//		String url = "https://graph.z.qq.com/moc2/token"; //wap
		
		String param = grant_type+"&"+client_id+"&"+client_secret+"&"+code+"&"+redirect_uri;

		String result = HttpRequest.httpGet(url, param);//返回信息
		String arr = result.split("&")[0];
		String access_token = arr.split("=")[1];

		return access_token;
	}
	
	public static String getOpenId(String token){
		String url = "https://graph.qq.com/oauth2.0/me";//pc
//		String url = "https://graph.z.qq.com/moc2/me";//wap
		String param = "access_token="+token;
		String result = HttpRequest.httpGet(url, param);//返回信息
		
		result = result.replace("callback(", "");
		result = result.replace(");", "");
		JSONObject json = JSONObject.parseObject(result);
		String openid = (String) json.get("openid");
		return openid;
	}
	
	public static String getUserInfo(String token,String openId){
		String url = "https://graph.qq.com/user/get_user_info";
		String access_token = "access_token="+token;
		String oauth_consumer_key ="oauth_consumer_key="+APP_ID;
		String openid = "openid="+openId;
		String param = access_token+"&"+oauth_consumer_key+"&"+openid;
		String result = HttpRequest.httpGet(url, param);//返回信息
		return result;
	}

}
