package com.app.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class TestReptile_zhizhu implements PageProcessor {
		  
	 static Set<Long> sets = new HashSet<Long>();
	 
	 public static Set<Long> running(String url) {
	  Spider.create(new TestReptile_zhizhu()).addUrl(url).run();
	  //返回到set里面
	  return sets;
	 }
	 
	 private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	 
	 public Site getSite() {
	  return site;
	 }
	 
	 public static void main(String[] args) {
		 List<String> urls = new ArrayList<String>();
		 urls.add("https://uland.taobao.com/coupon/edetail?e=1EF59Y91zI4N%2BoQUE6FNzHP6%2BEOhXh10Yxy7hjE6KO7%2FzA7lN%2B5%2FiOf8tgkdicwohBy%2FjLYmYaX9svuAdGTC1xpywujSvOp2nUIklpPPqYIZSmmb3RD6HyKwksv%2BGpdnSp%2F07C8ccEXM2X15BOsfxPpmhtf68SWB&pid=mm_112528044_18512801_65564943&af=1");
		 
		 for (String string : urls) {
			 //转换一下请求的接口
			String realurl = string.replace("/coupon/edetail?", "/cp/coupon?ctoken=CfLyeteurzVyIptVaDHliceland&");
			//调用爬取方法
			running(realurl);
		}
	}
	 
	 @SuppressWarnings("all")
	 public void process(Page page) {
	  page.setSkip(true);
	  
	  //爬取 之后 返回的数据
	  String text = page.getRawText();
	  
	  System.err.println(text);
	  
	  //把返回的数据 转换成 json
//	  JSONObject fromObject = JSONObject.fromObject(text);
	  
	  JSONObject fromObject = JSONObject.parseObject(text);
	  //获取json数据 里面的 结果集 ----根据键获取
	  JSONObject jsonObject = fromObject.getJSONObject("result");
	  
	  System.err.println(jsonObject);
	  
		  JSONObject item = jsonObject.getJSONObject("item");
		  
		  System.err.println(item);
		  
		  Long itemId = item.getLong("itemId");
		  sets.add(itemId);
	  
	 }
}
