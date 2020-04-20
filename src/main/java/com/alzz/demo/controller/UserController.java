package com.alzz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.common.ResponseBean;
import com.alzz.demo.core.annotation.AnnotationLog;
import com.alzz.demo.domain.User;
import com.alzz.demo.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户操作接口"}, description = "UserController")
public class UserController {

    @Autowired
    UserService userService;

    //{"page":"1","size":"5"}
    @AnnotationLog(remark = "查询")
    @PostMapping("/getUserPage")
    @ApiOperation(value = "查询用户", notes = "分页查询用户")
    public ResponseBean<PageInfo<User>> getUserPage(@RequestBody JSONObject params) {
        return userService.getUserPage(params);
    }

    //{"userName":"张三","password":"123"}
//    @AnnotationLog(remark = "查询")
//    @PostMapping("/login")
//    public ResponseBean<User> login(@RequestBody JSONObject params) {
//        String userName = params.getString("userName");
//        String password = params.getString("password");
//        Subject currentUser = SecurityUtils.getSubject();
//        //登录
//        try {
//            currentUser.login(new UsernamePasswordToken(userName, password));
//        } catch (IncorrectCredentialsException i) {
//            throw new ServiceException("密码输入错误");
//        }
//        //从session取出用户信息
//        User user = (User) currentUser.getPrincipal();
//        return new ResponseBean(user);
//    }

}
