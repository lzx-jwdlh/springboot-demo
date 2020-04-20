package com.alzz.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName TestController
 * @Description 测试接口
 * @Author lzx
 * @Date 2020/1/10 14:41
 */
@Controller
@RequestMapping("/test")
@Api(tags = {"test接口"}, description = "TestController")
public class TestController {

    @ApiOperation(value = "查询用户", notes = "根据用户ID查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true,
                    dataType = "Integer", paramType = "query")
    })
    @GetMapping("/index")
    public String test(){
        return "/index";
    }
}
