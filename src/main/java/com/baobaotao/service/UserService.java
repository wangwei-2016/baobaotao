package com.baobaotao.service;

import com.baobaotao.domain.User;

import java.util.Set;

/**
 * 用户业务逻辑接口
 * author Mr.WangWei
 * Create 2017 01 04 13:50
 */
public interface UserService {
    /**
     *  根据用户名和密码查询用户
     * @return  0：表示用户名或密码错误，1：存在并正确
     */
    Boolean hasMatchCount(String userName, String password);

    /**
     * 根据用户名查找User
     * @param userName 用户名
     * @return User
     */
    User findUserByUserName(String userName);

    /**
     * 根据用户名查找角色
     * @param userName 用户名
     * @return 角色
     */
    Set<String> findRoles(String userName);
    /**
     *  更新用户最后登录时间、ip、积分
     * @param user 用户
     */
    void loginSuccess(User user);
}
