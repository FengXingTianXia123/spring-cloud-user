package com.user.api;

import com.user.entity.UserInfoVo;
import com.user.entity.UserLoginVo;
import com.user.entity.UserRecordVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserAuthService {
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    String getToken(@RequestParam(value = "userId") long userId) throws Exception;

    @RequestMapping(value = "/saveRecord", method = RequestMethod.POST)
    String saveRecord(@RequestBody UserRecordVo record) throws Exception;

    @RequestMapping(value = "/updateUserRecord", method = RequestMethod.POST)
    String updateUserRecord(@RequestBody UserRecordVo record) throws Exception;

    @RequestMapping(value = "/getUserLogin", method = RequestMethod.GET)
    int getUserLogin(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) throws Exception;

    @RequestMapping(value = "/addUserLoginInfo", method = RequestMethod.POST)
    void addUserLoginInfo(@RequestBody UserInfoVo userInfoVo) throws Exception;

    @RequestMapping(value = "/getUserType", method = RequestMethod.GET)
    int getUserType(@RequestParam(value = "userName") String userName) throws Exception;
}
