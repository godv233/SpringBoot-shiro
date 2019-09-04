package com.example.demo.domain;

import lombok.Data;

/**
 * @author 曾伟
 * @date 2019/9/4 16:36
 */
@Data
public class User {
    private Integer userid;
    private String username;
    private String userpassword;
    private String perms;
}
