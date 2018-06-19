package com.user.api;

import com.user.entity.UserCountVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IUserCountService {

    @RequestMapping(value = "/getLoginMinuteByDay", method = RequestMethod.POST)
    List<String> getLoginMinuteByDay(@RequestBody Map<String,Object>map)throws Exception;

    @RequestMapping(value = "/getPie", method = RequestMethod.GET)
    List<UserCountVo> getPie(@RequestParam(value = "type") String type)throws Exception;

    @RequestMapping(value = "/getUserCountByDay", method = RequestMethod.POST)
    List<Integer> getUserCountByDay(@RequestBody Map<String,Object>map)throws Exception;
}
