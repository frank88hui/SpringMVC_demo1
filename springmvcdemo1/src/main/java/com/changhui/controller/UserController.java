package com.changhui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    //跳转到登录页面的方法
    @RequestMapping("/toLogin.action")
    public String toLogin() {
        return "login";
    }

    //登录
    @RequestMapping("/login.action")
    public String login(String username, String password, HttpSession session) {

        if (username != null && password != null) {
            if("zs".equals(username)&&"123".equals(password)){
                //登陆成功
                session.setAttribute("user",username+password);
                return "redirect:/itemList.action";
            }
        }
        return "redirect:/toLogin.action";
    }

}
