package com.changhui.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor {

    //执行处理器之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginIntercepter preHandle");
        //登陆成功放行


        //放行登录操作
        String requestURI = request.getRequestURI();
        if(requestURI.contains("login") || requestURI.contains("toLogin")){
            return true;
        }

        //登录不成功 跳转登录页面
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("/toLogin.action").forward(request,response);
            return false;
        }

        return true;
    }

    //在返回ModelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginIntercepter postHandle");
    }

    //在页面渲染完成之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginIntercepter afterCompletion");
    }
}
