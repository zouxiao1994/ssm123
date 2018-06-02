package com.zx.factory;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.zx.mappers.UsersMapper;

public class FilterChainDefinitionMapBuilder {
	
	
	/*public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> linkMap = new LinkedHashMap<>();
		
		linkMap.put("/login.jsp", "anon");
		linkMap.put("/shiro/login", "anon");
		linkMap.put("/shiro/logout", "logout");
		//linkMap.put("/admin.jsp", "authc,roles[admin]");
		linkMap.put("/admin.jsp", "authc");
		//linkMap.put("/user.jsp", "authc,roles[user]");
		//linkMap.put("/list.jsp", "user");
		linkMap.put("/list.jsp", "authc");
		linkMap.put("/**", "authc");
		
		return linkMap;
	}*/
	
}
