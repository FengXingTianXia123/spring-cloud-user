package com.user.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IUserCountService {
    @RequestMapping(value = "/getLoginMinuteByDay", method = RequestMethod.POST)
    List<String> getLoginMinuteByDay(@RequestBody Map<String,Object>map)throws Exception;
}
