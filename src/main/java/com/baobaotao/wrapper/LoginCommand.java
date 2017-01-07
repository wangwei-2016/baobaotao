package com.baobaotao.wrapper;

import java.io.Serializable;

/**
 * 登录表单封装对象
 * author Mr.WangWei
 * Create 2017 01 05 10:42
 */
public class LoginCommand implements Serializable {
    private String userName;

    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
