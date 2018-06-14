package com.user.auth.mapper;

import com.user.auth.entity.UserRecord;

import java.util.List;

public interface UserRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRecord record);

    int insertSelective(UserRecord record);

    UserRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRecord record);

    int updateByPrimaryKey(UserRecord record);

    //根据sessionId查询用户网站统计信息
    List<UserRecord> queryRecordList(String sessionId);

    int updateBySessionId(UserRecord record);


}