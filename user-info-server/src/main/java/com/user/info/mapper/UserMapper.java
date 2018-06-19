package com.user.info.mapper;

import com.user.info.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int selectByUserName(String name);

    User selectUserInfo(String name);

    int insertSelective(User record);
}