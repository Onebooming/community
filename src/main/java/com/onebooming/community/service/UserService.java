package com.onebooming.community.service;

import com.onebooming.community.mapper.UserMapper;
import com.onebooming.community.model.User;
import com.onebooming.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Oneboomingh
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(userExample);

        if(userList.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(userList.get(0));
        }
        else{
            //更新
            User newUser = userList.get(0);
            User user1 = new User();
            user1.setGmtModified(System.currentTimeMillis());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setName(user.getName());
            user.setToken(user.getToken());
            UserExample userExample1 = new UserExample();
            userExample1.createCriteria().andIdEqualTo(newUser.getId());
            userMapper.updateByExampleSelective(user1,userExample1);
        }
    }
}
