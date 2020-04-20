package com.alzz.demo.controller;

import com.alzz.demo.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShiroUtilsController
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/14 11:15
 */
@RestController
@RequestMapping("/shiro")
public class ShiroUtilsController {

    @Autowired
    ShiroService shiroService;

    @GetMapping("/noLogin")
    public String noLogin() {
//        throw new UnauthenticatedException("请先登录");
        return "请先登录";
    }

    @GetMapping("/noAuthorize")
    public String noAuthorize() {
//        throw new UnauthorizedException("暂无权限");
        return "暂无权限";
    }


//    @PostMapping("/getNowUser")
//    public User getNowUser() {
//        User u = (User) SecurityUtils.getSubject().getPrincipal();
//        return u;
//    }

    @GetMapping("/logout")
    public String logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        return "登出成功";
    }

    @GetMapping("/updatePermission")
    @ApiOperation(value = "更新权限", notes = "更新权限")
    public String updatePermission() {
        shiroService.updatePermission();
        return "更新成功";
    }

}
