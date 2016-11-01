package com.soddy.base.shiro.config;

import com.trs.base.entity.TbBaseUser;
import com.trs.base.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Shiro权限控制
 * @author xiao
 *
 */
public class ShiroRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	
//	@Resource
//	private LoginService loginService;
	@Resource
	private HttpSession session;
	
	private Set<String> roleSet = null; 	//角色集合
	private Set<String> permSet = null; 	//权限集合


	/**
	 * 执行授权 (访问功能判断是否具有权限)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		String idcard = (String)super.getAvailablePrincipal(principals);
		
//		if(session.getAttribute(SystemConst.Session.SESSION_ROLESET)!=null
//		   && session.getAttribute(SystemConst.Session.SESSION_PERMSET)!=null){
//
//			roleSet = (Set<String>) session.getAttribute(SystemConst.Session.SESSION_ROLESET);
//			permSet = (Set<String>) session.getAttribute(SystemConst.Session.SESSION_PERMSET);
//		} else {
//
//			roleSet = loginService.findUserRoles(idcard);
//			permSet = loginService.findUserPerms(idcard);
//			session.setAttribute(SystemConst.Session.SESSION_ROLESET, roleSet);
//			session.setAttribute(SystemConst.Session.SESSION_PERMSET, permSet);
//
//			logger.info("用户["+idcard+"]权限设置成功!");
//		}
//
//		authInfo.addRoles(roleSet);
//		authInfo.addStringPermissions(permSet);
		
		return authInfo;
	}
	
	

	/**
	 * 执行认证 （登陆）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String idcard = upToken.getUsername();
		char[] password = upToken.getPassword();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(TbBaseUser.class).add(Restrictions.eq("idcard", idcard));
		TbBaseUser loginUser = loginService.findOneByCriteria(criteria);
		
		if(loginUser != null){
        	AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(loginUser.getIdcard(), loginUser.getPassword(), getName()); 
        	if(password!=null && String.valueOf(password).equals(loginUser.getPassword())){
        		session.setAttribute(SystemConst.Session.SESSION_LOGINUSER, loginUser);
        	}
        	
        	return authcInfo;
        }
        return null;

	}
	
	
	
	
}
