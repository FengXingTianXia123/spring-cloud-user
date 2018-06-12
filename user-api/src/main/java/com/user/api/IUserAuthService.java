package com.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IUserAuthService {
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    String getToken(Integer id)throws Exception;
}
