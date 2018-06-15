package com.user.count.mapper;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import com.user.count.entity.Record;

import java.util.Date;
import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> countLoginMinuteByDay(String curTime);
}