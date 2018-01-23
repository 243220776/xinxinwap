package com.app.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSONObject;

public class HttpRequest {
	
	public static String httpGet(String url, String param){
		String result="";
		BufferedReader in=null;
		String urlName=url+"?"+param;
		
		try {
			URL reaUrl=new URL(urlName);
			URLConnection connection = reaUrl.openConnection();
			// 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            // 建立实际的连接
            connection.connect();
            
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            
            while ((line = in.readLine()) != null) {
                result += line;
            }
			
		} catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
		
		// 使用finally关闭输入流
		finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		return result;
	}
	
	
	
	public static void main(String[] args) {
//		String url="https://v.apistore.cn/api/c43";
//		String param="key=89b605bba1b48ce3c285f6c5fa09e044&bankcard=6217002450006040579";
		String url="https://v.apistore.cn/api/c43";
		String param="key=89b605bba1b48ce3c285f6c5fa09e044&bankcard=6217002450006040579";
		String data =httpGet(url,param);
		JSONObject fromObject=JSONObject.parseObject(data);
		System.err.println(data);
		System.out.println(fromObject.getInteger("error_code"));
	}
	
}
