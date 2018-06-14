package com.user.info.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserInfoService;

import com.user.entity.UserInfoVo;
import com.user.entity.UserRecordVo;
import com.user.info.entity.User;
import com.user.info.mapper.UserMapper;
import com.user.util.ClassCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Service
@RestController
public class UserServiceImpl implements IUserInfoService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserInfoVo getUser(@RequestParam("userId") long userId) throws Exception {
        UserInfoVo userInfo=new UserInfoVo();
        User user=userMapper.selectByPrimaryKey(userId);
        userInfo= (UserInfoVo)ClassCopy.copy(user,userInfo);
        UserRecordVo userRecord=new UserRecordVo();
        userRecord.setLoginTime(new Date());
        userInfo.setUserRecordVo(userRecord);
        System.out.println("--------"+JSON.toJSONString(user));
        System.out.println("--------"+user.getName());
        return userInfo;
    }


    @Override
    public int addUser(@RequestBody UserInfoVo userInfo) throws Exception {
        System.out.println("--------"+JSON.toJSONString(userInfo));
        User user=new User();
        user=(User)ClassCopy.copy(userInfo,user);
        int result=userMapper.insertSelective(user);
        return result;
    }
}
