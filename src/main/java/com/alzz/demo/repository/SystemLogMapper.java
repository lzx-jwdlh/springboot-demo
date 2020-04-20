package com.alzz.demo.repository;

import com.alzz.demo.core.annotation.DataSource;
import com.alzz.demo.domain.SystemLog;

import java.util.List;

@DataSource()
public interface SystemLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    int insertByBatch(List<SystemLog> list);
}