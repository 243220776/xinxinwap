package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.TelCode;
import com.app.service.TelCodeService;

@RestController
@RequestMapping("/telCode")
public class TelCodeController {
	
	@Autowired
	TelCodeService telCodeService;
	
	/**
	 * 获取验证码
	 * @param telcode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getCode",method=RequestMethod.POST)
	public String getCode(TelCode telcode,HttpServletRequest request){
		return telCodeService.getCode(telcode,"SMS_74915023");
	}
	
	//验证短信接口
	@RequestMapping(value="validateCode",method=RequestMethod.POST)
	public String validateCode(String phone, Integer code){
		return telCodeService.validateCode(phone,code);
	}
	
}
