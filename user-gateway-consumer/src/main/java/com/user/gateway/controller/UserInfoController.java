package com.user.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.user.entity.UserInfoVo;
import com.user.entity.UserRecordVo;
import com.user.gateway.client.UserInfoAuthClient;
import com.user.gateway.client.UserInfoCountClient;
import com.user.gateway.client.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    @Autowired
    private UserInfoAuthClient userInfoAuthClient;

    @Autowired
    private UserInfoCountClient userInfoCountClient;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public Object getUser(@RequestParam(value = "userId") long userId) throws Exception {
        System.out.println("------------userId-----" + userId);
        if (userId == 0) {
            userId = 1;
        }

        UserInfoVo userInfo = userInfoFeignClient.getUser(userId);
        System.out.println("-----" + JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Object getUser(@RequestBody UserInfoVo userInfo) throws Exception {

        System.out.println("-----" + JSON.toJSONString(userInfo));
        int result = userInfoFeignClient.addUser(userInfo);
        return result;
    }

    @RequestMapping(value = "/getAuthInfo", method = RequestMethod.GET)
    public Object getAuthInfo(@RequestParam(value = "userId") long userId) throws Exception {
        System.out.println("---userId--" + JSON.toJSONString(userId));
        String str = userInfoAuthClient.getToken(userId);
        System.out.println("-----" + JSON.toJSONString(str));
        return str;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long userId = Long.parseLong(request.getParameter("userId"));
        UserInfoVo userInfo = new UserInfoVo();
        userInfo = userInfoFeignClient.getUser(userId);
        saveSession(request, userInfo);
        return userInfo;
    }

    public void saveSession(HttpServletRequest request, UserInfoVo userInfo) throws Exception {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(70);
        Object obj = session.getAttribute("userInfo");
        String sessionId = session.getId();
        System.out.println("----" + JSON.toJSONString(userInfo));
        System.out.println("--sessionId--" + sessionId);
        UserRecordVo record = new UserRecordVo();

        record.setUserId(userInfo.getId());
        record.setUserName(userInfo.getName());
        record.setSessionId(sessionId);
        userInfo.setUserRecordVo(record);

        if (obj instanceof UserInfoVo) {

        } else {
            session.setAttribute("userInfo", userInfo);
            userInfoAuthClient.saveRecord(record);
        }

    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if(session !=null){
            session.removeAttribute("userInfo");
            session.invalidate();
        }
    }

    @RequestMapping(value = "/getLoginMinuteByDay", method = RequestMethod.POST)
    public Object getLoginMinuteByDay(@RequestBody Map<String,Object>map) throws Exception {
        System.out.println("----ru can-----" + JSON.toJSONString(map));
        List resList = userInfoCountClient.getLoginMinuteByDay(map);
        return resList;
    }
}
