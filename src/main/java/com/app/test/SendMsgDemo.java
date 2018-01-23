package com.app.test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SendMsgDemo {
	
	 /** 短信接口的请求地址---正式环境 */  
    public static final String SMS_REQUEST_URL = "https://eco.taobao.com/router/rest";
    //沙箱环境	 https://gw.api.tbsandbox.com/router/rest
  
    /** ################# 需要根据情况填写下面的属性 #################### */  
    /** 应用的Key */  
    private static final String APP_KEY = "23732217";  
    /** 　应用的签名 */  
    private static final String APP_SECRET = "d98bf7b953715fcf78dedf89e0f9a068";  
    /** 短信签名 */  
    private static final String SMS_FREE_SIGN_NAME = "身份验证";  
    /** 短信模板ID ---身份验证 */  
    private static final String SMS_TEMPLATE_CODE = "SMS_59905131";
	
    public static void main(String[] args) throws ApiException {
    	
    	String phone = "15139911763";
    	String code = "8888";
    	
		sendMsg(phone,code);
	}
    
    	
	public static Boolean sendMsg(String phone,String code) {
		
		/**  
         * 创建淘宝客户端 参数一：请求URL（短信接口的请求地址） 参数二：应用的Key 参数三：应用的签名  
         * 方法里面的参数用常量定义。可以随时改  
         */ 
		TaobaoClient client = new DefaultTaobaoClient(SMS_REQUEST_URL, APP_KEY, APP_SECRET); 
		
		 /** 构建短信发送请求对象 */ 
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		
		 /** ######################### 封装请求参数  ############################ */  
        /**  
         * 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，  
         * 在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用 (可选)  
         */  
        req.setExtend("雅鑫-成功传来消息");  
        /**  
         * 短信类型，传入值请填写normal (必须)  
         */  
        req.setSmsType("normal");  
        
        /**  
         * 短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。 示例：SMS_59905131 (必须)  
         */  
        req.setSmsTemplateCode(SMS_TEMPLATE_CODE);  
        /**  
         * 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。  
         * 示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，  
         * 传参时需传入{"code":"1234","product":"alidayu"}  
         * 验证码${number}，您正进行身份验证，打死不告诉别人！ (可选)  
         */  
        req.setSmsParamString("{\"code\":\"" + code + "\",\"product\":\"张雅鑫\"}"); 
        
        req.setRecNum(phone);
        
        /**  
         * 短信签名，传入的短信签名必须是在阿里大于“管理中心-短信签名管理”中的可用签名。  
         * 如“阿里大于”已在短信签名管理中通过审核，则可传入”阿里大于“（传参时去掉引号）作为短信签名。  
         * 短信效果示例：【阿里大于】欢迎使用阿里大于服务。 (必须)  
         */  
        req.setSmsFreeSignName(SMS_FREE_SIGN_NAME); 
        
        
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			
			System.out.println(rsp.getBody());
			//短信发送成功        Success = true
			System.out.println(rsp.getResult().getSuccess());
			
			//短信发送成功        ErrCode = 0
			System.out.println(rsp.getResult().getErrCode());
			
			//返回
			return rsp.getResult().getSuccess();
			
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}
}
