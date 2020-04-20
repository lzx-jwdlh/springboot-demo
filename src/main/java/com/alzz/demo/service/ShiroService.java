package com.alzz.demo.service;

import java.util.Map;

/**
 * @ClassName ShiroService shiro 动态更新权限
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/14 15:25
 */
public interface ShiroService {

    Map<String, String> loadFilterChainDefinitions();

    /**
     * 动态修改权限
     */
    void updatePermission();
}
