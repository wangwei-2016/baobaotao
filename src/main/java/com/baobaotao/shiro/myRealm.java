package com.baobaotao.shiro;

import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Administrator on 2017/1/20.
 */
@Component
public class myRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 权限认证
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles = userService.findRoles(userName);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = getUserInfo(usernamePasswordToken);
        String userName=user.getUserName();
        String userPwd = user.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
            return null;
        }
        if (!userService.hasMatchCount(userName, userPwd)) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(userName,userPwd,getName());
    }

    private User getUserInfo(UsernamePasswordToken usernamePasswordToken) {
        User user = new User();
        user.setUserName(usernamePasswordToken.getUsername());
        user.setPassword(String.valueOf(usernamePasswordToken.getPassword()));
        return user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
