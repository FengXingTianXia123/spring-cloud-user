package com.user.auth.server.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserAuthService;
import com.user.auth.server.client.UserInfoFeignClient;
import com.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class UserAuthServiceImpl implements IUserAuthService {

    @Autowired
    UserInfoFeignClient userInfo;

    @Override
    public String getToken(@RequestParam("userId") long userId) throws Exception {

       System.out.print("======"+JSON.toJSONString(userInfo.getUser(userId)));
        return JSON.toJSONString(userInfo.getUser(userId));
    }

    @Override
    public String saveSession(@RequestBody UserInfo userInfo) throws Exception {

        return null;
    }

    @Override
    public String updateUserRecord(@RequestBody UserInfo userInfo) throws Exception {
        return null;
    }
}
