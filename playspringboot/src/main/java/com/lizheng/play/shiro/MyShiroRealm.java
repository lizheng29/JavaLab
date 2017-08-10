package com.lizheng.play.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm{

	@Override
	public String getName() {
		return "lizheng";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
	    String userId = (String)SecurityUtils.getSubject().getPrincipal();
	    SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
	    //根据用户ID查询角色（role），放入到Authorization里。
	    /*Map<String, Object> map = new HashMap<String, Object>();
	    map.put("user_id", userId);
	    List<SysRole> roleList = sysRoleService.selectByMap(map);
	    Set<String> roleSet = new HashSet<String>();
	    for(SysRole role : roleList){
	        roleSet.add(role.getType());
	    }*/
	    //实际开发，当前登录用户的角色和权限信息是从数据库来获取的，我这里写死是为了方便测试
	    Set<String> roleSet = new HashSet<String>();
	    roleSet.add("admin");
	    info.setRoles(roleSet);
	    //根据用户ID查询权限（permission），放入到Authorization里。
	    /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
	    Set<String> permissionSet = new HashSet<String>();
	    for(SysPermission Permission : permissionList){
	        permissionSet.add(Permission.getName());
	    }*/
	    Set<String> permissionSet = new HashSet<String>();
	    permissionSet.add("添加");
	    info.setStringPermissions(permissionSet);
	    return info;
	}
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码
		/*if (!"lizheng".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"lizheng".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}*/
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
