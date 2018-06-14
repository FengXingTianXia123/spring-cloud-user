package com.user.util;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;


import java.util.List;

public class ClassCopy {

    public static Object copy(Object orign, Object target){
        MapperFactory factory  = new DefaultMapperFactory.Builder().build();
        target = factory.getMapperFacade().map(orign, target.getClass());
        return target;
    }

    public static Object copyList(List<?> orignList,Object target){
        MapperFactory factory  = new DefaultMapperFactory.Builder().build();
        List<?> targetList = factory.getMapperFacade().mapAsList(orignList, target.getClass());
        return targetList;
    }
}
