package com.kou.controller;

import com.kou.domain.SysLog;
import com.kou.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author JIAJUN KOU
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Before("execution(* com.kou.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        //当前时间就是开始访问的时间
        visitTime=new Date();
        //这是具体要访问的类
        clazz=joinPoint.getTarget().getClass();
        //获取访问的方法的名称
        String methodName=joinPoint.getSignature().getName();

        //获取访问方法的参数
        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0){
            //只能获取无参数的方法
            method=clazz.getMethod(methodName);
        }else {
            Class[] classArgs=new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i]=args[i].getClass();
            }
            method=clazz.getMethod(methodName, classArgs);
        }
    }


    @After("execution(* com.kou.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){

        //1.计算出访问时间
       long time=System.currentTimeMillis()-visitTime.getTime();
       //2.获取访问的url；通过反射来完成操作
        String url="";
        if(clazz!=null||method!=null|| clazz!=LogAop.class){
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();

                //获取方法上的value值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=classValue[0]+methodValue[0];
                }
            }
        }
        //3.获取访问的ip 通过request对象
        String ip=request.getRemoteAddr();
        //4.获取当前操作的用户 ；通过springSecurity里面的对象获取.这种方式更容易
        //从上下文中获取当前登录用户
        SecurityContext context= SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //将日志相关信息，封装到SysLog对象中
        SysLog sysLog=new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setVisitTime(visitTime);

        //调用service完场操作
        sysLogService.save(sysLog);
    }

}
