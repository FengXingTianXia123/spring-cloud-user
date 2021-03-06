package com.user.util;

import com.alibaba.fastjson.JSON;
import com.user.entity.UserInfoVo;
import com.user.info.entity.User;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassCopy {

    public static Object copy(Object orign, Object target){
        MapperFactory factory  = new DefaultMapperFactory.Builder().build();
        target = factory.getMapperFacade().map(orign, target.getClass());
        return target;
    }

    public static void main(String[] args){
          User user=new User();
          user.setId(1l);
          user.setName("sdfsd");
        UserInfoVo userInfo=new UserInfoVo();
        userInfo=(UserInfoVo)copy(user,userInfo);
        System.out.println("------------"+JSON.toJSONString(userInfo));

        List<User>uList=new ArrayList<>();
        uList.add(user);
        List<UserInfoVo> l= (List<UserInfoVo>) copyList(uList,userInfo);
        System.out.println("------------"+JSON.toJSONString(l));


    }

    public static Object copyList(List<?> orignList,Object target){
        MapperFactory factory  = new DefaultMapperFactory.Builder().build();
        List<?> targetList = factory.getMapperFacade().mapAsList(orignList, target.getClass());
        return targetList;
    }
}
