package com.baobaotao.service.impl;

import com.baobaotao.dao.UserMapper;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import com.baobaotao.service.LoginLogService;
import com.baobaotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

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
    private LoginLogService logService;

    public int hasMatchCount(String userName, String password) {
        int count = userMapper.getMatchCount(userName, password);
        return count > 0 ? 1 : 0;
    }

    public User findUserByUserName(String userName) {
        User user = userMapper.getByUserName(userName);
        Assert.notNull(user,"用户不存在");
        return user;
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
