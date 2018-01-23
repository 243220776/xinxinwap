package com.app.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class APIStore {
	
	/**
	 * HTTP的Post请求方式
	 * @param strUrl 访问地址
	 * @param param 参数字符串
	 * */
	public static String requestPost(String strUrl, String param) {
		String returnStr = null; // 返回结果定义
		URL url = null;
		HttpURLConnection httpURLConnection = null;

		try {
			url = new URL(strUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("POST"); // post方式
			httpURLConnection.connect();
//			System.out.println("ResponseCode:" + httpURLConnection.getResponseCode());
			//POST方法时使用
			byte[] byteParam = param.getBytes("UTF-8");
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
			out.write(byteParam);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			returnStr = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return returnStr;
	}

	
	/**
	 * 获取内容
	 */
	public static String getContent(String bankcard) {
	    String url="https://v.apistore.cn/api/c43";
        String param="key=89b605bba1b48ce3c285f6c5fa09e044&bankcard="+bankcard;
        
	    String returnStr = null;
	    returnStr = requestPost(url, param);
	    	    
	    return returnStr;
	}
	
	
}
