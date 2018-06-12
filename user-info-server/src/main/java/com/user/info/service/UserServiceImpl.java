package com.user.info.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserInfoService;
import com.user.entity.UserInfo;
import com.user.info.entity.User;
import com.user.info.mapper.UserMapper;
import com.user.util.ClassCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class UserServiceImpl implements IUserInfoService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfo getUser(@RequestParam("userId") long userId) throws Exception {
        UserInfo userInfo=new UserInfo();
        User user=userMapper.selectByPrimaryKey(userId);
        userInfo= (UserInfo)ClassCopy.copy(user,userInfo);
        System.out.println("--------"+JSON.toJSONString(user));
        System.out.println("--------"+user.getName());
        return userInfo;
    }
}
