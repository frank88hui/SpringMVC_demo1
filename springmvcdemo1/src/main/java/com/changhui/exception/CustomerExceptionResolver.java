package com.changhui.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CustomerExceptionResolver implements HandlerExceptionResolver {

    /**
     * 异常处理思路
     * 1：判断异常的种类，究竟是业务异常，还是系统运行时异常
     * 2：取出异常信息
     * 3：跳转到异常页面，将异常信息带到页面上
     * 4：发邮件，发短信，通知相关人员异常问题，以及异常信息
     */

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView view = new ModelAndView();

        String exceptMsg;
        if (ex instanceof CustomerException) {
            //业务异常
            CustomerException exception = (CustomerException) ex;
            exceptMsg = exception.getExceptMsg();
        } else {
            //运行期异常
            StringWriter sWriter = new StringWriter();
            PrintWriter writer = new PrintWriter(sWriter);
            ex.printStackTrace(writer);
            exceptMsg = sWriter.toString();
        }

        view.setViewName("error");
        view.addObject("except", exceptMsg);
        return view;

    }
}
