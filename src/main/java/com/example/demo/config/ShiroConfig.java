package com.example.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.demo.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 曾伟
 * @date 2019/9/4 10:34
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建Realm
     *
     * @return
     */
    @Bean
    public UserRealm getRealm() {
        return new UserRealm();
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建ShiroFilterFactoryBean
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加Shiro的内置过滤器：实现权限相关的拦截
        /**
         * 常用的过滤器
         *  anon:无需认证（登录）就可以访问
         *  authc:必须认证才能访问
         *  user:如果使用rememberMe的功能可以直接访问
         *  perms:该资源必须得到资源权限才可以访问
         *  role:该资源必须得到角色权限才可以访问
         */
        //LinkedHashMap可以保证拦截顺序
        Map<String, String> filterMap = new LinkedHashMap<>();
        //设置拦截的资源
        filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");
        //放行url
        filterMap.put("/test", "anon");
        filterMap.put("/hello", "anon");
        filterMap.put("/toLogin", "anon");

        //授权过滤器,设置拦截的url和权限字符串
        // 授权拦截后，shiro会自动跳转一个未授权的页面 可以设置
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");


        //通配符，使得 所有资源都被拦截
        filterMap.put("/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //修改跳转的登录的页面 shiro默认是login.jsp
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        return shiroFilterFactoryBean;
    }

    /**
     * 配置ShiroDialect， shiro整合thymeleaf标签
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
