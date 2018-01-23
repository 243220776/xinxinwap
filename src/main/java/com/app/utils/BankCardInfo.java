package com.app.utils;

import com.alibaba.fastjson.JSONObject;

public class BankCardInfo {
	
	public static void main(String[] args) {
//		String bankcard="6228480732396407411";
		String bankcard="6217770007303245";
		getCard(bankcard);
 
	}
	public static void getCard(String bankcard){
		String returnStr=APIStore.getContent(bankcard);
		
		JSONObject forJsonObject = JSONObject.parseObject(returnStr);
	    System.out.println(forJsonObject);
	    
	    if(forJsonObject.getIntValue("error_code")==0){
	    	JSONObject json = forJsonObject.getJSONObject("result");
	    	
	    	String bankname = json.getString("bankname");
	    	String bankimage = json.getString("bankimage");
	    	String province = json.getString("province");
	    	String city = json.getString("city");
	    	
	    	System.out.println(bankname+"----"+bankimage+"----"+province+"----"+city);
	    }else{
	    	System.err.println("失败");
	    }
	    

	}
}
