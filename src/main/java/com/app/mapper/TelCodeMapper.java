package com.app.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.app.domain.TelCode;

@Mapper
public interface TelCodeMapper {
	
	Integer saveTelCode(TelCode telCode);
	
	TelCode findByTelAndCodeAndStatus(@Param("phone")String phone,@Param("code")Integer code,@Param("status")Integer status);
	
	Integer updateStatus(@Param("status")Integer status,@Param("id")Integer id);
	
}
