package com.user.auth.mapper;

import com.user.auth.entity.UserRecord;

public interface UserRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRecord record);

    int insertSelective(UserRecord record);

    UserRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRecord record);

    int updateByPrimaryKey(UserRecord record);
}