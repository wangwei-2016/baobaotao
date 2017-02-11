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
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping("/main")
    public ModelAndView Main(ModelAndView modelAndView) {
        modelAndView.setViewName("main");
        modelAndView.addObject("message", "hello spring mvc2");
        return modelAndView;
    }

    @RequestMapping("/loginCheck")
    public String LoginCheck(HttpServletRequest request, ModelAndView modelAndView, LoginCommand loginCommand) {
      /*  String userName = loginCommand.getUserName();
        String userPwd = loginCommand.getUserPwd();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
            return new ModelAndView("index", "error", "用户名或密码不能为空");
        }
        if (!userService.hasMatchCount(userName, userPwd)) {
            return new ModelAndView("index", "error", "用户名或密码错误");
        }
        User user = userService.findUserByUserName(userName);
        user.setLastIp(request.getRemoteHost());
        userService.loginSuccess(user);*/
        User user = new User();
        user.setUserName(loginCommand.getUserName());
        user.setPassword(loginCommand.getUserPwd());
        String info = loginUser(user);
        if (!"SUCC".equals(info)) {
//            return new ModelAndView("index", "error", "用户名或密码错误");
            return "redirect:/index";
        }

//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("redirect:/main");
        return "redirect:/main";
    }

    @RequestMapping("/logout")
    public String LoginOut(HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            try {
                subject.logout();
            } catch (Exception ex) {
            }
        }
        return "redirect:/index";
    }

    private String loginUser(User user) {
//        if (isRelogin(user)) return "SUCC";
        return shiroLogin(user);
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

    private boolean isRelogin(User user) {
        Subject us = SecurityUtils.getSubject();
        if (us.isAuthenticated()) {
            return true; // 参数未改变，无需重新登录，默认为已经登录成功
        }
        return false; // 需要重新登陆
    }
}
