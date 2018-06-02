package com.zx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zx.entity.ZxUsers;
import com.zx.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;

	
	@RequestMapping(value="/getList.html",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getList(){
		List<ZxUsers> list = userServiceImpl.getList();
		for (ZxUsers z : list) {
			System.out.println(z.toString());
		}
		return JSON.toJSONString(list);
	}
}
