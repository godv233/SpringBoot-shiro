package com.example.demo.shiro;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 曾伟
 * @date 2019/9/4 10:37
 * 自定义的realm
 */
public class UserRealm  extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //对资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加资源的授权字符串
        //info.addStringPermission("user:add");硬编码
        //获取当前的登陆用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //到数据查询当前用户登录的授权字符串
        User dbUser = userService.getUserById(user.getUserid());
        info.addStringPermission(dbUser.getPerms());
        return info;
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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getUser(token.getUsername());
        //编写shiro的判断逻辑，判断用户名和密码是否正确
        AuthenticationInfo authenticationInfo=null;
        //1.判断用户名
        if (user==null){
            System.out.println("用户名不正确");
            //返回null,底层会抛出UnknownAccountException
        }else {
            authenticationInfo=new SimpleAuthenticationInfo(user,user.getUserpassword(),getName());
        }
        return authenticationInfo;
    }
}
