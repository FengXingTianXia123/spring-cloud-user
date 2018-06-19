package com.user.count.service;

import com.alibaba.fastjson.JSON;
import com.user.api.IUserCountService;
import com.user.count.entity.Record;
import com.user.count.entity.UserCount;
import com.user.count.mapper.RecordMapper;
import com.user.entity.UserCountVo;
import com.user.util.ClassCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RestController
public class UserCountServiceImpl implements IUserCountService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<String> getLoginMinuteByDay(@RequestBody Map<String, Object> map) throws Exception {
        List<String> list = (List<String>) map.get("map");
        long userId= Long.parseLong(map.get("userId")+"");
        System.out.println("----ru can:" + JSON.toJSONString(list));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        List<String> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            long curTime = sdf.parse(list.get(i)).getTime();
            System.out.println("curTime-----------" + curTime);
            long nextTime = curTime + 24 * 60 * 60 * 1000;
            long day = 24 * 60 * 60 * 1000;
            long t = 0;

            List<Record> record_list = recordMapper.countLoginMinuteByDay(userId,list.get(i), sdf.format(new Date(nextTime)));
            System.out.println("-------------recordlist-----" + JSON.toJSONString(record_list));

            for (Record record : record_list) {
                if (record.getLoginTime().getTime() < curTime) {
                    if ((record.getLogoutTime().getTime() - record.getLoginTime().getTime()) / day < 2) {
                        t += record.getLogoutTime().getTime() - curTime;
                    } else {
                        t += day;
                    }
                } else {
                    if (record.getLogoutTime().getTime() < nextTime) {
                        t += record.getLogoutTime().getTime() - record.getLoginTime().getTime();
                        System.out.println("---------else----------" + t);
                    } else {
                        t += nextTime - record.getLoginTime().getTime();
                    }
                }
            }
            System.out.println("--------------" + t);
            resList.add((t / 60000) + "");
            System.out.println("----fanhui jie guo:" + JSON.toJSONString(resList));

        }
        return resList;
    }

    @Override
    public List<UserCountVo> getPie(@RequestParam(value = "type") String type) throws Exception {
        List<UserCountVo> uVo_list = new ArrayList<>();
        if ("age".equals(type)) {
            List<UserCount> uCount_list = recordMapper.getPieByAge();
            System.out.println("-----------" + JSON.toJSONString(uCount_list));
            uVo_list = (List<UserCountVo>) ClassCopy.copyList(uCount_list, new UserCountVo());
        } else {
            List<UserCount> uCount_list = recordMapper.getPieByGender();
            System.out.println("-----------" + JSON.toJSONString(uCount_list));
            uVo_list = (List<UserCountVo>) ClassCopy.copyList(uCount_list, new UserCountVo());
        }
        return uVo_list;
    }

    @Override
    public List<Integer> getUserCountByDay(@RequestBody Map<String, Object> map) throws Exception {
        System.out.println("---map------"+JSON.toJSONString(map));
        List<String> list = (List<String>) map.get("map");
        System.out.println("---------"+JSON.toJSONString(list));
        List<Integer> res_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int count = 0;
            count = recordMapper.getUserCountByDay(list.get(i));
            res_list.add(count);
        }
        return res_list;
    }


}
