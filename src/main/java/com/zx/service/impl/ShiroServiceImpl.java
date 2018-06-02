package com.zx.service.impl;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.zx.service.ShiroService;

@Service
public class ShiroServiceImpl implements ShiroService {
	
	
//	@RequiresRoles(value={"admin"})
	public void shiroTest(){
		Session session = SecurityUtils.getSubject().getSession();
		
		Object value = session.getAttribute("key");
		System.out.println("session -- value=="+value);
		
		System.out.println("ShiroService------"+new Date());
	}
}
