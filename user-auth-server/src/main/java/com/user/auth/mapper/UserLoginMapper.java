package com.user.auth.mapper;

import com.user.auth.entity.UserLogin;
import org.apache.ibatis.annotations.Param;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);

    int selectLoginInfo(@Param(value = "userName") String userName, @Param(value = "password") String password);

    int selectUserType(String userName);
}