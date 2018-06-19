package com.user.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.user.entity.UserCountVo;
import com.user.entity.UserInfoVo;
import com.user.entity.UserLoginVo;
import com.user.entity.UserRecordVo;
import com.user.gateway.client.UserInfoAuthClient;
import com.user.gateway.client.UserInfoCountClient;
import com.user.gateway.client.UserInfoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result",userInfo);
        return resultMap;
    }

    /*
    注册用户
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public Object getUser(@RequestBody UserInfoVo userInfoVo) throws Exception {
        Map<String,Object> resultMap=new HashMap<>();
        //登记info表
        int status;
        int result = userInfoFeignClient.addUser(userInfoVo);
        if(result == 0){
            status = 0;
            resultMap.put("status",status);
            return resultMap;
        }else{
            status = 1;
            //登记login表
            userInfoAuthClient.addUserLoginInfo(userInfoVo);
            resultMap.put("status",status);
            return resultMap;
        }
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
        Map<String,Object>resultMap=new HashMap<>();
        List resList = userInfoCountClient.getLoginMinuteByDay(map);
        resultMap.put("result",resList);
        return resultMap;
    }

    @RequestMapping(value = "/getPie", method = RequestMethod.GET)
    public Object getPie(@RequestParam(value = "type") String type) throws Exception {
        System.out.println("----ru can-----" + type);
        Map<String,Object>resultMap=new HashMap<>();
        List<UserCountVo> uCountList = userInfoCountClient.getPie(type);
        resultMap.put("result",uCountList);
        return resultMap;
    }

    @RequestMapping(value = "/getUserCountByDay", method = RequestMethod.POST)
    public Object getUserCountByDay(@RequestBody Map<String,Object>map) throws Exception {
        Map<String,Object>resultMap=new HashMap<>();
        System.out.println("----ru can-----" + map);
        List resList = userInfoCountClient.getUserCountByDay(map);
        resultMap.put("result",resList);
        return resultMap;
    }

    /*
    注册判断用户名是否已存在
     */
    @RequestMapping(value="/getUserByName", method=RequestMethod.GET)
    public Object getUser(@RequestParam(value="userName") String userName) throws Exception {
        System.out.println("------------userName-----"+userName);
        int result = userInfoFeignClient.getUserByName(userName);
        System.out.println("-----"+result);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result",result);
        return resultMap;
    }

    /*
    用户登录校验
     */
    @RequestMapping(value = "/loginCheck",method = RequestMethod.POST)
    public Object loginCheck(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String userName=(String)request.getParameter("userName");
        String password=(String)request.getParameter("password");
        int result = userInfoAuthClient.getUserLogin(userName,password);
        System.out.println("-----"+result);
        UserInfoVo userInfo =new UserInfoVo();
        userInfo=userInfoFeignClient.getUserInfo(userName);
        saveSession(request,userInfo);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("result",result);
        return resultMap;
    }

    /*
    获取用户信息
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Object getUserInfo(@RequestParam(value = "userName") String userName) throws Exception {
        System.out.println("------------userName-----" + userName);
        UserInfoVo userInfo = userInfoFeignClient.getUserInfo(userName);
        System.out.println("-----" + JSON.toJSONString(userInfo));
        return JSON.toJSONString(userInfo);
    }

    /*
    获取用户角色
     */
    @RequestMapping(value = "/getUserType", method = RequestMethod.GET)
    public Object getUserType(@RequestParam(value = "userName") String userName) throws Exception {
        System.out.println("------------userName-----" + userName);
        int type = userInfoAuthClient.getUserType(userName);
        System.out.println("-----" + type);
        Map<String,Object> TypeMap=new HashMap<>();
        TypeMap.put("type",type);
        return TypeMap;
    }

}
