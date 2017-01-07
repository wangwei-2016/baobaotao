package com.baobaotao.service;

import com.baobaotao.domain.LoginLog;

/**
 * 登录日志业务逻辑接口
 * authoracer
 * Create 2017 01 04 14:13
 */
public interface LoginLogService {
    /**
     * 插入用户登录日志
     * @param log 日志对象
     */
    void insertLoginLog(LoginLog log);
}
