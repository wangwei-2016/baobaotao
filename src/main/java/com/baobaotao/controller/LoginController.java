package com.baobaotao.controller;

import com.baobaotao.domain.User;
import com.baobaotao.service.UserService;
import com.baobaotao.wrapper.LoginCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录控制器
 * author Mr.WangWei
 * Create 2017 01 05 10:02
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView Login(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("message", "hello spring mvc2");
        return modelAndView;
    }

    @RequestMapping("/loginCheck")
    public ModelAndView LoginCheck(HttpServletRequest request, ModelAndView modelAndView, LoginCommand loginCommand) {
        String userName = loginCommand.getUserName();
        String userPwd = loginCommand.getUserPwd();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
            return new ModelAndView("index", "error", "用户名或密码不能为空");
        }
        if (!userService.hasMatchCount(userName, userPwd)) {
            return new ModelAndView("index", "error", "用户名或密码错误");
        }
        User user = userService.findUserByUserName(userName);
        user.setLastIp(request.getRemoteHost());
        userService.loginSuccess(user);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("main");
        return modelAndView;
    }

    private String loginUser(User user) {
        shiroLogin(user);
    }

    private String shiroLogin(User user) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (UnknownAccountException ex) {
            return "用户不存在或者密码错误！";
        } catch (IncorrectCredentialsException ex) {
            return "用户不存在或者密码错误！";
        } catch (AuthenticationException ex) {
            return ex.getMessage(); // 自定义报错信息
        } catch (Exception ex) {
            ex.printStackTrace();
            return "内部错误，请重试！";
        }
        return "SUCC";
    }
}
