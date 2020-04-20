package com.alzz.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.common.ResponseBean;
import com.alzz.demo.domain.User;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName UserServer
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/10 15:26
 */
public interface UserService {

    ResponseBean<PageInfo<User>> getUserPage(JSONObject params);


}
