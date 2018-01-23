package com.app.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMS_TB_Utils {

	/** 短信接口的请求地址---正式环境 */
	public static final String SMS_REQUEST_URL = "https://eco.taobao.com/router/rest";
	/** ################# 需要根据情况填写下面的属性 #################### */
	/** 应用的Key */
	private static final String APP_KEY = "24517185";
	/** 应用的签名 */
	private static final String APP_SECRET = "9bc87603075d2e57eed05cd15d55d93c";
	/** 短信签名 */  
	private static final String SMS_FREE_SIGN_NAME = "新新";

	
	/**
	 * 
	 * @param phone : 电话号码
	 * @param paramsString : 参数字符串
	 * @param tempId : 模板id
	 * @return
	 */
	public static Boolean sendMsg(String phone,String paramsString, String tempId) {

		TaobaoClient client = new DefaultTaobaoClient(SMS_REQUEST_URL, APP_KEY, APP_SECRET);

		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();

		req.setSmsType("normal");

		req.setSmsTemplateCode(tempId);

		req.setSmsParamString(paramsString);
		
		req.setRecNum(phone);

		req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);

		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			
			if (rsp == null || rsp.getResult()==null) {
				return false;
			}else{
				
				return rsp.getResult().getSuccess();
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return false;
	}
}
