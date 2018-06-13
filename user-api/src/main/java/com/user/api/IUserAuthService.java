package com.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserAuthService {
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    String getToken(@RequestParam(value = "userId") long userId)throws Exception;
}
