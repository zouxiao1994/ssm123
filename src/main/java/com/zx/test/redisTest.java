package com.zx.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zx.common.RedisUtil;
import com.zx.entity.ZxUsers;
import com.zx.service.UserService;

public class redisTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-spring.xml");
		RedisUtil redisUtil = (RedisUtil) context.getBean("RedisUtil");
		UserService userService = (UserService) context.getBean("userServiceImpl");
		/*redisUtil.put("k0", "呵呵安心");
		
		System.out.println(redisUtil.get("k0"));
		*/
		
		/*Map<String, Object> map = new HashMap<>();
		map.put("name", "邹潇");
		map.put("age", "23");
		map.put("sex", "男");
		redisUtil.put("k7", map);*/
		
	/*	
		ZxUsers zx = new ZxUsers();
		
		zx.setName("邹潇");
		zx.setAge(22);
		zx.setBirth(new Date());
		zx.setId(1);
		zx.setSex("男");
		
		redisUtil.put("zxuser", zx);
		
		ZxUsers zxUsers = redisUtil.get("zxuser", ZxUsers.class);
		System.out.println(zxUsers.toString());*/
		
		
		List<ZxUsers> list = userService.getList();
		
		for (ZxUsers zxUsers : list) {
			redisUtil.put(zxUsers.getId().toString(), zxUsers);
		}
		for (ZxUsers zxUsers : list) {
			ZxUsers s = redisUtil.get(zxUsers.getId().toString(), ZxUsers.class);
			System.out.println(s);
		}
		
	}

}
