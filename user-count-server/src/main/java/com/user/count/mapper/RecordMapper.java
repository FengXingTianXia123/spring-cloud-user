package com.user.count.mapper;

import com.user.count.entity.Record;
import com.user.count.entity.UserCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> countLoginMinuteByDay(@Param("userId") long userId,@Param("curTime") String curTime,@Param("nextTime")String nextTime);

    List<UserCount> getPieByAge();

    List<UserCount> getPieByGender();

    int getUserCountByDay(String date);
}