package com.alzz.demo.repository;

import com.alzz.demo.core.annotation.DataSource;
import com.alzz.demo.domain.SysPermissionInit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@DataSource()
public interface SysPermissionInitMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermissionInit record);

    int insertSelective(SysPermissionInit record);

    SysPermissionInit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermissionInit record);

    int updateByPrimaryKey(SysPermissionInit record);

    /** 功能描述: 查询url资源
     * @param
    * @return: java.util.List<com.alzz.demo.domain.SysPermissionInit>
    * @Author: lzx
    * @Date: 2020/1/14 15:00
    */
    List<SysPermissionInit> selectAllOrderBySort();
}