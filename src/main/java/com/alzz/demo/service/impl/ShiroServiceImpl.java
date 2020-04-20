package com.alzz.demo.service.impl;

import com.alzz.demo.domain.SysPermissionInit;
import com.alzz.demo.repository.SysPermissionInitMapper;
import com.alzz.demo.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShiroServiceImpl
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/14 15:26
 */
@Service
public class ShiroServiceImpl implements ShiroService {


//    @Autowired
//    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    SysPermissionInitMapper sysPermissionInitMapper;

    /**
     * 初始化权限
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        List<SysPermissionInit> list = sysPermissionInitMapper.selectAllOrderBySort();
        for (SysPermissionInit sysPermissionInit : list) {
            filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
                    sysPermissionInit.getPermissionInit());
        }
        return filterChainDefinitionMap;
    }


    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
//        synchronized (shiroFilterFactoryBean) {
//            AbstractShiroFilter shiroFilter = null;
//            try {
//                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
//                        .getObject();
//            } catch (Exception e) {
//                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
//            }
//            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
//                    .getFilterChainResolver();
//            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
//                    .getFilterChainManager();
//            // 清空老的权限控制
//            manager.getFilterChains().clear();
//            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
//            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
//            // 重新构建生成
//            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
//            for (Map.Entry<String, String> entry : chains.entrySet()) {
//                String url = entry.getKey();
//                String chainDefinition = entry.getValue().trim().replace(" ", "");
//                manager.createChain(url, chainDefinition);
//            }
//            System.out.println("更新权限成功！！");
//        }
    }

}
