package com.user.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.user.entity.UserInfo;
import com.user.gateway.client.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @RequestMapping(value="/getUserById", method=RequestMethod.GET)
    public Object getUser(@RequestParam(value="userId") long userId) throws Exception {
        System.out.println("------------userId-----"+userId);
        if (userId == 0) {
            userId = 1;
        }

        UserInfo userInfo = userInfoFeignClient.getUser(userId);
        System.out.println("-----"+JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }
}
