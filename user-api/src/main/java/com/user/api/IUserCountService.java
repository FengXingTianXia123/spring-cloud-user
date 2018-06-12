package com.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IUserCountService {
    @RequestMapping(value = "/getLogCount", method = RequestMethod.GET)
    int logCount(Integer userId)throws Exception;
}
