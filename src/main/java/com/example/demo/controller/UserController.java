package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 曾伟
 * @date 2019/9/3 21:28
 */
@Controller
public class UserController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "ok";
    }
}
