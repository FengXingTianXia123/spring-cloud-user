package com.user.api;

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

}
