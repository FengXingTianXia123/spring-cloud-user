package com.user.util;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    public static List<String> getDatesBetweenTwoDate(String start, String end) throws ParseException {
        System.out.println("------start------"+start);
        System.out.println("------end------"+end);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate=sdf.parse(start);
        Date endDate=sdf.parse(end);

        List<String> lDate = new ArrayList<String>();
        lDate.add(start);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间

        cal.setTime(beginDate);
//      System.out.print("------"+sdf.format(cal.getTime()));
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
//            if (endDate.getTime()-cal.getTime().getTime()>0) {
                lDate.add(sdf.format(cal.getTime()));
            } else {
                break;
            }
        }
        lDate.add(end);// 把结束时间加入集合
        return lDate;
    }

    public static void main(String args[]) throws ParseException {
        Map map=new HashMap();
        map.put("startDate","2018-01-01");
        map.put("endDate","2018-03-01");
        List<String>list=getDatesBetweenTwoDate(map.get("startDate").toString(),map.get("endDate").toString());
        System.out.println(JSON.toJSONString(list));
    }
}
