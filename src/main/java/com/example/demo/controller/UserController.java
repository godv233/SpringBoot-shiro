package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 曾伟
 * @date 2019/9/3 21:28
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 测试环境搭建
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/hello")
    public User hello() {
        return userService.getUser("111");
    }

    @RequestMapping("/test")
    public String testThymeleaf(Model model) {
        //数据存入model
        model.addAttribute("name", "曾伟");
        System.out.println("进入方法");
        return "test";
    }

    @RequestMapping("/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "/user/update";
    }

    @RequestMapping("/login")
    public String login() {
        System.out.println("登录页面");
        return "/login";
    }

    /**
     * 关键的登录控制器
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(String name, String password, Model model) {
        /**
         * 使用shiro编写认证操作
         */
        //1.获得subject
        Subject subject = SecurityUtils.getSubject();
        //2.token封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3.执行登陆方法，没有抛出异常就是登录成功。否则失败
        try {
            subject.login(token);
            //登陆成功,跳转test
            //重定向到方法
            return "redirect:/test";
        } catch (UnknownAccountException e) {
            //不同的异常表示不同的登陆错误。UnknownAccountException表示用户名不存在
            model.addAttribute("message", "用户名不存在");
            return "/login";
        } catch (IncorrectCredentialsException e) {
            //该异常表示密码错误
            model.addAttribute("message", "密码错误");
            //直接跳转到页面，而不是controller方法
            return "/login";
        }
    }


}
