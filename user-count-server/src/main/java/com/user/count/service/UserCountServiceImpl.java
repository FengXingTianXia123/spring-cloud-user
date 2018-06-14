package com.user.count.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserCountService;
import com.user.count.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
@RestController
public class UserCountServiceImpl implements IUserCountService {
    @Autowired
    RecordMapper recordMapper;
    @Override
    public List<String> getLoginMinuteByDay(@RequestParam List<String> list) throws Exception {
        System.out.println("----ru can:"+JSON.toJSONString(list));
        List<String>resList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            int count=recordMapper.countLoginMinuteByDay(list.get(i));
            resList.add(count+"");
        }
        return resList;
    }
}
