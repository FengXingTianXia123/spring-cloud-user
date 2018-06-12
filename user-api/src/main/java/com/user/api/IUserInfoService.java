package com.user.api;

import com.user.entity.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserInfoService {
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    UserInfo getUser(@RequestParam(value = "userId") long userId) throws Exception;
}
