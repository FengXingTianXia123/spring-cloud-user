package com.user.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IUserCountService {
    @RequestMapping(value = "/getLoginMinuteByDay", method = RequestMethod.GET)
    List<String> getLoginMinuteByDay(@RequestParam(value = "days")List<String> dayList)throws Exception;
}
