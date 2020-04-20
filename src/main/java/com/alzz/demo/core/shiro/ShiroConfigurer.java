//package com.alzz.demo.core.shiro;
//
//import com.alzz.demo.domain.SysPermissionInit;
//import com.alzz.demo.repository.SysPermissionInitMapper;
//import com.alzz.demo.repository.UserMapper;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
///**
// * @ClassName ShiroConfigurer
// * @Description TODO
// * @Author lzx
// * @Date 2020/1/14 11:28
// */
//@Configuration
//public class ShiroConfigurer {
//
//    @Autowired
//    SysPermissionInitMapper sysPermissionInitMapper;
//
//    /**
//     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
//     */
//    @Bean
//    public Realm realm() {
//        return new CustomRealm();
//    }
//
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        /**
//         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
//         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
//         * 加入这项配置能解决这个bug
//         */
//        creator.setUsePrefix(true);
//        return creator;
//    }
//
//    @Autowired
//    UserMapper userMapper;
//    /**
//     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
//     * @return
//     */
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//        chain.addPathDefinition("/swagger-ui.html", "anon");
//        chain.addPathDefinition("/swagger/**", "anon");
//        chain.addPathDefinition("/swagger-resources/**", "anon");
//        chain.addPathDefinition("/v2/**", "anon");
//        chain.addPathDefinition("/webjars/**", "anon");
//        chain.addPathDefinition("/configuration/**", "anon");
//        List<SysPermissionInit> sysPermissionInits = sysPermissionInitMapper.selectAllOrderBySort();
//        for (int i = 0; i < sysPermissionInits.size(); i++) {
//            chain.addPathDefinition(sysPermissionInits.get(i).getUrl(), sysPermissionInits.get(i).getPermissionInit());
//        }
//
////        chain.addPathDefinition( "/user/testxss", "authc, roles[admin]");
////        chain.addPathDefinition( "/shiro/logout", "anon");
////        chain.addPathDefinition( "/user/getUserPage", "anon");
////        chain.addPathDefinition( "/user/login", "anon");
////        chain.addPathDefinition( "/**", "authc");
//        return chain;
//    }
//
//}
