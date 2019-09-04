package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 曾伟
 * @date 2019/9/4 19:04
 */
@Mapper
public interface UserMapper {
    /**
     * get
     * @param username
     * @return
     */
    User getUserByName(String username);
}
