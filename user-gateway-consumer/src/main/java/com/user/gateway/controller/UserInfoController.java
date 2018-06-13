package com.user.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.user.entity.UserInfo;
import com.user.gateway.client.UserInfoAuthClient;
import com.user.gateway.client.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Autowired
    private UserInfoAuthClient userInfoAuthClient;

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

    @RequestMapping(value="/addUser", method=RequestMethod.POST)
    public Object getUser(@RequestBody UserInfo userInfo) throws Exception {

        System.out.println("-----"+JSON.toJSONString(userInfo));
        int result = userInfoFeignClient.addUser(userInfo);
        return result;
    }

    @RequestMapping(value="/getAuthInfo", method=RequestMethod.GET)
    public Object getAuthInfo(@RequestParam(value="userId") long userId) throws Exception {
        System.out.println("---userId--"+JSON.toJSONString(userId));
        String str = userInfoAuthClient.getToken(userId);
        System.out.println("-----"+JSON.toJSONString(str));
        return str;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Object login(HttpServletRequest request, HttpServletResponse response)throws Exception{

        return null;
    }
}
