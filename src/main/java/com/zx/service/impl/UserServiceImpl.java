package com.zx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zx.entity.ZxUsers;
import com.zx.mappers.UserMapper;
import com.zx.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;


	public List<ZxUsers> getList() {
		
		return userMapper.getList();
	}

}
