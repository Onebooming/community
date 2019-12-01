package com.onebooming.community.service;

import com.onebooming.community.mapper.UserMapper;
import com.onebooming.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Oneboomingh
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){
        User newUser = userMapper.findByAccountId(user.getAccountId());
        if(newUser == null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }
        else{
            //更新
            newUser.setGmtModified(System.currentTimeMillis());
            newUser.setAvatarUrl(user.getAvatarUrl());
            newUser.setName(user.getName());
            newUser.setToken(user.getToken());
            userMapper.update(newUser);
        }
    }
}
