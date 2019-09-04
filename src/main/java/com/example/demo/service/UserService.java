package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 曾伟
 * @date 2019/9/4 17:16
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUser(String username){
        return userMapper.getUserByName(username);
    }
}

