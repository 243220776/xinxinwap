package com.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.app.domain.TelCode;
import com.app.mapper.TelCodeMapper;
import com.app.service.TelCodeService;
import com.app.utils.SMS_TB_Utils;

@Service
public class TelCodeserviceImpl implements TelCodeService{
	
	@Autowired
	TelCodeMapper telCodeMapper;

	@Override
	public String getCode(TelCode telcode, String tempID) {
		JSONObject json = new JSONObject();
		Integer code = (int) (Math.random()*900000+100000);
		
		telcode.setStatus(1);
		telcode.setTime(new Date());
		telcode.setCode(code);
//		telcode.setSession_id(1);//TODO 本地验证码
		
		try {
			String paramString =  "{\"number\":\"" + code + "\"}";
			Boolean  sms_ret = SMS_TB_Utils.sendMsg(telcode.getPhone(), paramString, tempID);
//			Boolean  sms_ret = true;//TODO 本地验证码
			if (sms_ret) {
				Integer saveTelCode = telCodeMapper.saveTelCode(telcode);
				if (null != saveTelCode && 0 < saveTelCode) {
					json.put("code",1);
					json.put("msg", "success");
					return json.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		json.put("code", 0);
		json.put("msg", "fail");
		return json.toString();
	}
	
	@Override
	public String validateCode(String phone, Integer code) {

		JSONObject ret = new JSONObject();
		
		TelCode  findByTelAndCodeAndStatus= telCodeMapper.findByTelAndCodeAndStatus(phone,code,1);

		if (null != findByTelAndCodeAndStatus) {

			if (null != findByTelAndCodeAndStatus.getCode()) {
				Date date=new Date();
				long  between = date.getTime() - findByTelAndCodeAndStatus.getTime().getTime();
				if (between>600000||between<=0){//10分钟
					ret.put("code", -1);
					ret.put("msg", "验证码超时！");

					return ret.toString();
				}
			}

			if (findByTelAndCodeAndStatus.getId() != null) {
				Integer updateStatus = telCodeMapper.updateStatus(2, findByTelAndCodeAndStatus.getId());

				if (updateStatus > 0) {
					ret.put("code", 1);
					ret.put("msg", "success");
					return ret.toString();
				}
			}
		}
		ret.put("code", 0);
		ret.put("msg", "验证码错误！");
		return ret.toString();
	}
	
}
