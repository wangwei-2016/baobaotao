package com.baobaotao.service.impl;

import com.baobaotao.dao.RoleMapper;
import com.baobaotao.dao.UserMapper;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.Role;
import com.baobaotao.domain.User;
import com.baobaotao.service.LoginLogService;
import com.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户业务逻辑接口实现层
 * author Mr.WangWei
 * Create 2017 01 04 13:51
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private LoginLogService logService;

    public Boolean hasMatchCount(String userName, String password) {
        int count = userMapper.getMatchCount(userName, password);
        return count > 0;
    }

    public User findUserByUserName(String userName) {
        User user = userMapper.getByUserName(userName);
        Assert.notNull(user,"用户不存在");
        List<Role> roles= roleMapper.queryRoleByUserId(user.getUserId());
        user.setRoles(roles);
        return user;
    }

    public Set<String> findRoles(String userName) {
        User user = userMapper.getByUserName(userName);
        List<Role> roles= roleMapper.queryRoleByUserId(user.getUserId());
        Set<String> stringSet = new HashSet<String>();
        for (Role role : roles) {
            stringSet.add(role.getRoleName());
        }
        return stringSet;
    }

    public void loginSuccess(User user) {
        user.setCredits(user.getCredits() + 5);
        user.setLastVisit(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        LoginLog log = new LoginLog();
        log.setUserId(user.getUserId());
        log.setIp(user.getLastIp());
        log.setLoginDatetime(user.getLastVisit());
        logService.insertLoginLog(log);
    }
}
