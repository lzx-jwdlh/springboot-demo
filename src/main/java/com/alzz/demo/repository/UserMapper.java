package com.alzz.demo.repository;
import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectById(@Param("id")Long id);

    User selectByUserName(@Param("userName")String userName);




    /**
     * 功能描述: TODO 查询所有用户
     *
     * @param params
     * @return: java.util.List<com.alzz.demo.domain.User>
     * @Author: lzx
     * @Date: 2020/1/14 10:24
     */
    List<User> selectByAll(JSONObject params);

    /** 功能描述: 根据用户id查询用户角色
     * @param userId
    * @return: java.util.List<java.lang.String>
    * @Author: lzx
    * @Date: 2020/1/14 10:44
    */
    List<String> getRolesByUserId(String userId);

    /** 功能描述: 根据用户id查询用户权限
     * @param userId
    * @return: java.util.List<java.lang.String>
    * @Author: lzx
    * @Date: 2020/1/14 10:44
    */
    List<String> getPermsByUserId(String userId);


}