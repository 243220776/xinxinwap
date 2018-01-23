package com.app.service;
import com.app.domain.TelCode;
public interface TelCodeService {
	
	String getCode(TelCode telcode,String tempID);
	
	String validateCode(String phone,Integer code);
}
