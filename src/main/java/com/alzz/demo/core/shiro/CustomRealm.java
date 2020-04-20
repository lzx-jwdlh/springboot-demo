//package com.alzz.demo.core.shiro;
//
//import com.alzz.demo.domain.User;
//import com.alzz.demo.repository.UserMapper;
//import com.alzz.demo.service.UserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @ClassName CustomRealm
// * @Description TODO 自定义如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
// * @Author lzx
// * @Date 2020/1/14 10:46
// */
//public class CustomRealm extends AuthorizingRealm {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserMapper userMapper;
//    /**
//     * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
//     */
////    {
////        //设置用于匹配密码的CredentialsMatcher
////        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
////        hashMatcher.setHashAlgorithmName("md5");
////        hashMatcher.setStoredCredentialsHexEncoded(true);
////        //加密的次数
////        hashMatcher.setHashIterations(1024);
////        this.setCredentialsMatcher(hashMatcher);
////    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        if (principalCollection == null) {
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//
//        User user = (User) getAvailablePrincipal(principalCollection);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(user.getRoles());
//        info.setStringPermissions(user.getPerms());
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
//        String username = upToken.getUsername();
//        if (username == null) {
//            throw new AccountException("Null usernames are not allowed by this realm.");
//        }
//        User userDB = userMapper.selectByUserName(username);
//        if (userDB == null) {
//            throw new UnknownAccountException("No account found for admin [" + username + "]");
//        }
//        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
//        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
//        List<String> roleList = userMapper.getRolesByUserId(userDB.getId().toString());
//        List<String> permList = userMapper.getPermsByUserId(userDB.getId().toString());
//        Set<String> roles = new HashSet(roleList);
//        Set<String> perms = new HashSet(permList);
//        userDB.setRoles(roles);
//        userDB.setPerms(perms);
//
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPassword(), getName());
//        return info;
//    }
//}
