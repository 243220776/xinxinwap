package com.app.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class TestUrl {
	
	public static void main(String[] args) {
		
		String strURL = " https://detail.tmall.com/item.htm?spm=a2156.1676643.0002.2.VQ4mWx&pos=1&acm=301211.1003.1.278270&id=536762604916&scm=1003.1.301211.ITEM_536762604916_278270 ";
		String URL = " https://detail.tmall.com/item.htm?spm=a2156.1676643.0002.2.VQ4mWx&pos=1&acm=301211.1003.1.278270&id=536762604916&scm=1003.1.301211.ITEM_536762604916_278270 ";
		
		UrlPage(strURL);
		TruncateUrlPage(strURL);
		URLRequest(URL);
		forAss();
		
	}
	
	/**
	 * 解析出url请求的路径，包括页面
	 * 
	 * @param strURL
	 *            url地址
	 * @return url路径
	 */
	
	public static String UrlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;

		strURL = strURL.trim().toLowerCase();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			System.err.println(strURL.length());//
			if (arrSplit.length > 1) {
				System.err.println(arrSplit.length);//
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
					System.err.println(strPage);//
				}
			}
		}

		return strPage;
	}
	
	/**
	 * 去掉url中的路径，留下请求参数部分
	 * 
	 * @param strURL
	 *            url地址
	 * @return url请求参数部分
	 */
	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;

		strURL = strURL.trim().toLowerCase();

		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
//					System.out.println(strAllParam);
				}
			}
		}

		return strAllParam;
	}
	
	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();

		String[] arrSplit = null;

		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组 www.2cto.com
		arrSplit = strUrlParam.split("[&]");
		System.err.println(arrSplit);
		
		for (String strSplit : arrSplit) {
			System.out.println(strSplit);
			
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
	
	public static void forAss(){
		
//		String [] ss ={"aa","bb","cc","dd"};
		List<String> list = new ArrayList<String>();
		list.add("aabb5441545");
		list.add("fydhgsfhygg");
		
		for(String i : list){
			System.out.println(i);
		}
	}

}
