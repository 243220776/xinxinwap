package com.app.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.mapper.RoleMapper;
import com.app.service.RoleService;

@Service
public class RoleServiceImpl implements  RoleService{
	
	@Autowired
	RoleMapper roleMapper;


}
