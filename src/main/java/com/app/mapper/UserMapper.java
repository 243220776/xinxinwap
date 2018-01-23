package com.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.app.domain.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from user where id = #{id}")
	User getUser(@Param("id")Integer id);
	
	@Select("select * from user where username = #{userName} or phone = #{userName} or uID =#{userName}")
	User getByNameOrPhone(@Param("userName")String userName);
	
	@Select("select phone from user where phone = #{phone}")
	User phoneExist(@Param("phone")String phone);
	
	@Select("select userName from user where userName = #{userName}")
	User userExist(@Param("userName")String userName);
	
	@Select("SELECT MAX(uID) FROM user LIMIT 1")
	Integer getUID();
	
	@Select("select id,phone from user where phone= #{phone}")
	User getUerIdAndPhone(@Param("phone") String phone);
	
	Integer addUser(User user);
	
	Integer updateUser(User user);
	
}
