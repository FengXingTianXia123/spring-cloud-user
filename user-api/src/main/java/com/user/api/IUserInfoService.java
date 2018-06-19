package com.user.api;

import com.user.entity.UserInfoVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserInfoService {
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    UserInfoVo getUser(@RequestParam(value = "userId") long userId) throws Exception;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    int addUser(@RequestBody UserInfoVo userInfoVo) throws Exception;

    @RequestMapping(value = "/getUserByName", method = RequestMethod.GET)
    int getUserByName(@RequestParam(value = "userName") String userName) throws Exception;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    UserInfoVo getUserInfo(@RequestParam(value = "userName") String userName) throws Exception;

}
