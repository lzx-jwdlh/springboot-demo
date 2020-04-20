package com.alzz.demo.repository;

import com.alzz.demo.core.annotation.DataSource;
import com.alzz.demo.domain.SysRole;

@DataSource()
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}