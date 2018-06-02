package com.zx.controller;


import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zx.service.ShiroService;
import com.zx.service.impl.ShiroServiceImpl;

@Controller
@RequestMapping("/shiro")
public class LoginController {
	
	@Autowired
	private ShiroService shiroServiceImpl;
	
	@RequiresRoles(value={"admin"})
	@RequestMapping("/shiroTest")
	public String shiroTest(HttpSession session){
		session.setAttribute("key", "valur123");
		shiroServiceImpl.shiroTest();
		return "redirect:/list.jsp";
	}
	

	
	@RequestMapping("/login")
	public String login(@RequestParam String uname,@RequestParam String pwd){
		
		//获取当前登录的用户
		Subject currentUser = SecurityUtils.getSubject();
		
		
		//如果用保护是没有被认证的情况下（未登录）
		if(!currentUser.isAuthenticated()){
			//将用户名和密码分装成UsernamePasswordToken 对象
			UsernamePasswordToken token = new UsernamePasswordToken(uname, pwd);
			//记住我
			//token.setRememberMe(true);
			
			try {
				
				System.out.println("1:"+token.hashCode());
				//执行登录
				currentUser.login(token);
				
				//所有的认证的异常的父类
			} catch (AuthenticationException e) {
				
				System.out.println("===============登录失败==="+e);
			}catch(Exception e2){
				System.out.println("===============登录失败==="+e2);
			}
			
			
		}
			
		return "redirect:/list.jsp";
	}
	
	
	
	
}
