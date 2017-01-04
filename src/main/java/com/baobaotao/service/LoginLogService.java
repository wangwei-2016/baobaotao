package com.baobaotao.service;

import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;

/**
 * 登录日志业务逻辑接口
 * authoracer
 * Create 2017 01 04 14:13
 */
public interface LoginLogService {

    void insertLoginLog(LoginLog log);
}
