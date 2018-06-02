package com.zx.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		System.out.println("[SecondRealm  doGetAuthenticationInfo]");
		
		//将AuthenticationToken 类型的参数转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		
		// 从UsernamePasswordToken 中获取username
		String username = upToken.getUsername();
		
		// 调用数据库方法，从数据库中查询username 对应的数据记录
		System.out.println("从数据库中获取username"+username+"所对应的用户信息！");
		
		//如用户不存在，可以抛出UnknownAccountException 异常
		if("unknown".equals(username)){
			throw new UnknownAccountException("用户不存在！");
		}
		
		//根据用户的信息情况，决定是否要抛出其他的 AuthenticationException 异常
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁定！");
		}
		
		// 根据用户情况来构建AuthenticationInfo 对象并返回，通常使用的实现类为SimpleAuthenticationInfo
		
		//以下信息是从数据库中获取的
		// principal：认证的实体信息  可以是username  也可以是用户表对应的用户实体类对象
		Object principal = username;
		// 密码：credentials
		Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
		
		//realmName 当前realmName对象的name 调用父类的getName方法即可
		String realmName = getName();
		
		if("admin".equals(username)){
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
		}else if("user".equals(username)){
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
		}
		
		//盐值 加密
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		//SimpleAuthenticationInfo info  =new SimpleAuthenticationInfo(principal, credentials, realmName); info  =new SimpleAuthenticationInfo(principal, credentials, realmName);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}
	
	
	
	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";
		String credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println("SHA1加密result========"+result);
   
	}


}
