package com.user.auth.server.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserAuthService;
import com.user.auth.entity.UserLogin;
import com.user.auth.entity.UserRecord;
import com.user.auth.mapper.UserLoginMapper;
import com.user.auth.mapper.UserRecordMapper;
import com.user.auth.server.client.UserInfoFeignClient;
import com.user.entity.UserInfoVo;
import com.user.entity.UserLoginVo;
import com.user.entity.UserRecordVo;
import com.user.util.ClassCopy;
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
    @Autowired
    UserRecordMapper userRecordMapper;

    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public String getToken(@RequestParam("userId") long userId) throws Exception {

       System.out.print("======"+JSON.toJSONString(userInfo.getUser(userId)));
        return JSON.toJSONString(userInfo.getUser(userId));
    }

    @Override
    public String saveRecord(@RequestBody UserRecordVo userRecordVo) throws Exception {
        System.out.println("--saveRecord--------");
        UserRecord userRecord=new UserRecord();
        userRecord=(UserRecord)ClassCopy.copy(userRecordVo,userRecord);
        System.out.println("------保存入参：-----"+JSON.toJSONString(userRecord));
        int result= userRecordMapper.insertSelective(userRecord);
        return result+"";
    }

    @Override
    public String updateUserRecord(@RequestBody UserRecordVo userRecordVo) throws Exception {
        System.out.println("--进入updateUserRecord--------");
        UserRecord userRecord=new UserRecord();
        userRecord=(UserRecord)ClassCopy.copy(userRecordVo,userRecord);
        System.out.println("------修改入参：-----"+JSON.toJSONString(userRecord));
        int result= userRecordMapper.updateBySessionId(userRecord);
        return result+"";
    }

    @Override
    public int getUserLogin(@RequestParam("userName") String userName, @RequestParam(value = "password") String password) throws Exception {
        int result =userLoginMapper.selectLoginInfo(userName,password);
        System.out.println("--------"+result);
        return result;
    }

    @Override
    public void addUserLoginInfo(@RequestBody UserInfoVo userInfoVo) throws Exception {
        UserLogin userLogin = new UserLogin();
        userLogin=(UserLogin)ClassCopy.copy(userInfoVo,userLogin);
        //添加用户名密码到t_user_login表
        userLoginMapper.insert(userLogin);

    }

    @Override
    public int getUserType(@RequestParam("userName") String userName) throws Exception {
        int type =userLoginMapper.selectUserType(userName);
        System.out.println("--------"+type);
        return type;
    }

}
