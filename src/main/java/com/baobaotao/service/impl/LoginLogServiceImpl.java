package com.baobaotao.service.impl;

import com.baobaotao.dao.LoginLogMapper;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;
import com.baobaotao.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 登陆日志业务逻辑实现层
 * author Mr.WangWei
 * Create 2017 01 04 14:14
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper logMapper;

    public void insertLoginLog(LoginLog log) {
        logMapper.insert(log);
    }

    public void insertLoginLog(User user) {
        LoginLog log = new LoginLog();
        log.setUserId(user.getUserId());
        log.setIp(user.getLastIp());
        log.setLoginDatetime(new Date());

        logMapper.insert(log);
    }
}
