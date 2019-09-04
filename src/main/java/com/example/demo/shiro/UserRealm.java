package com.example.demo.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author 曾伟
 * @date 2019/9/4 10:37
 * 自定义的realm
 */
public class UserRealm  extends AuthorizingRealm {
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设账号和密码
        String name="godv";
        String password="123456";

        //编写shiro的判断逻辑，判断用户名和密码是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        AuthenticationInfo authenticationInfo=null;
        //1.判断用户名
        if (!token.getUsername().equals(name)){
            System.out.println("用户名不正确");
            //返回null,底层会抛出UnknownAccountException
        }else {
            authenticationInfo=new SimpleAuthenticationInfo("",password,"");
        }
        return authenticationInfo;

    }
}
