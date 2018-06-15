package com.user.count.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserCountService;
import com.user.count.entity.Record;
import com.user.count.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RestController
public class UserCountServiceImpl implements IUserCountService {
    @Autowired
    RecordMapper recordMapper;
    @Override
    public List<String> getLoginMinuteByDay(@RequestBody Map<String, Object> map) throws Exception {
        List<String>list=(List<String>) map.get("map");
        System.out.println("----ru can:"+JSON.toJSONString(list));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        List<String>resList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            List<Record>record_list=recordMapper.countLoginMinuteByDay(list.get(i));
            long curTime  = sdf.parse(list.get(i)).getTime();
            long nextTime = curTime+24*60*60*1000;
            long preTime  = curTime-24*60*60*1000;
            long t=0;
            for(Record record:record_list){
                  if(record.getLogoutTime().getTime()>nextTime){
                      t+= record.getLogoutTime().getTime()-nextTime;
                      t+= nextTime-record.getLoginTime().getTime();
                  }else if(record.getLogoutTime().getTime()>curTime && record.getLoginTime().getTime()<curTime){
                      t+= record.getLogoutTime().getTime();
                  }else {
                      t+= record.getLogoutTime().getTime()-record.getLoginTime().getTime();
                  }
            }
            resList.add((t/60000)+"");
            System.out.println("----fanhui jie guo:"+JSON.toJSONString(resList));

        }
        return resList;
    }


}
