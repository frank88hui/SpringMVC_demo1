package com.changhui.proxy;

import com.changhui.pojo.Items;
import com.changhui.pojo.Logs;
import com.changhui.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
public class ItemServiceProxy {

    @Autowired
    LogService logService;

    @AfterReturning(value = "execution(* com.changhui.service.ItemServiceImpl.*(..))", returning = "object")
    public void writeLog(JoinPoint joinPoint, Object object) {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            args = new String[]{""} ;
        }
        if (object == null) {
            object = "";
        }
        System.out.println("目标方法参数是" + args[0]);
        System.out.println("执行完返回值是" + object);

        for (Object arg : args) {
            System.out.println(arg);
        }

        Integer productId = 0;
        if (args[0] instanceof Integer) {
            productId = (Integer) args[0];
        } else if (args[0] instanceof Items) {
            productId = ((Items) args[0]).getId();
        }

        Logs logs = new Logs();
        logs.setProductId(productId);
        logs.setCreatetime(new Date());
        logs.setMethod(joinPoint.getSignature().getName());
        logs.setArgs(Arrays.toString(args).length()>250?Arrays.toString(args).substring(0,250):Arrays.toString(args));
        logs.setReturns(object.toString().length()>250?object.toString().substring(0,250):object.toString());
        logService.insert(logs);
    }

}
