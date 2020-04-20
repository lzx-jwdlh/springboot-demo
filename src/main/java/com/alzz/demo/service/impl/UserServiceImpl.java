package com.alzz.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.common.ResponseBean;
import com.alzz.demo.domain.User;
import com.alzz.demo.repository.UserMapper;
import com.alzz.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServerImpl
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/10 15:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResponseBean<PageInfo<User>> getUserPage(JSONObject params) {
        Integer page = params.getInteger("page");
        Integer size = params.getInteger("size");
        PageHelper.startPage(page, size);
        List<User> users = userMapper.selectByAll(params);
        return new ResponseBean<>(new PageInfo<>(users));
    }
}
