package com.example.demo.mapper.dal;

import com.example.demo.domain.User;

/**
 * @author 曾伟
 * @date 2019/9/4 16:39
 */
public interface UserMapper {
    /**
     * 通过username得到password
     * @param username
     * @return
     */
    User getByUsername(String username);
}
