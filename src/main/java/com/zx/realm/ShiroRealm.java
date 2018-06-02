package com.zx.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.zx.entity.Users;
import com.zx.mappers.UsersMapper;

//AuthenticatingRealm
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UsersMapper usersMapper;	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		//System.out.println("doGetAuthenticationInfo"+token.hashCode());
		System.out.println("[ShiroRealm  doGetAuthenticationInfo]");
		
		//将AuthenticationToken 类型的参数转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		
		// 从UsernamePasswordToken 中获取username
		String username = upToken.getUsername();
		//System.out.println("============表单传来的密码==="+upToken.getPassword());
		
		// 调用数据库方法，从数据库中查询username 对应的数据记录
		System.out.println("从数据库中获取username"+username+"所对应的用户信息！");
		Users user = usersMapper.getUsersByName(username);
		//System.out.println("============================"+user.getPassword());
		//如用户不存在，可以抛出UnknownAccountException 异常
		if(user == null){
			throw new UnknownAccountException("用户不存在！");
		}
		
		//根据用户的信息情况，决定是否要抛出其他的 AuthenticationException 异常
		if("monster".equals(username)){
			throw new LockedAccountException("用户被锁定！");
		}
		
		// 根据用户情况来构建AuthenticationInfo 对象并返回，通常使用的实现类为SimpleAuthenticationInfo
		
		
		//以下信息是从数据库中获取的
		// principal：认证的实体信息  可以是username  也可以是用户表对应的用户实体类对象
		Object principal = user;
//		Object principal = username;
		// 密码：credentials
		//Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
		
		System.out.println("========================pwd: "+user.getPassword());
		Object credentials = user.getPassword();
		
		//realmName 当前realmName对象的name 调用父类的getName方法即可
		String realmName = getName();
		
		/*if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)){
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}*/
		
		//盐值 加密
		//ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		SimpleAuthenticationInfo info  =new SimpleAuthenticationInfo(principal, credentials, realmName);
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}
	
	
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println("MD5加密result========"+result);
   
	}



	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}



	/*//授权 会被shiro 回调的方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//1 从PrincipalCollection 中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		
		//2 利用用户登录的信息来分辨登录用户的角色或权限（可能要查询数据库）
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			roles.add("admin");
		}
		
		//3 创建SimpleAuthorizationInfo 对象，并设置其roles 属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4 返回SimpleAuthorizationInfo 对象
		
		return info;
	}

*/
}
