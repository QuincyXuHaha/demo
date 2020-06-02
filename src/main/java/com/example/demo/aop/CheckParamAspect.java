package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xuguangquan
 * @date 2020/6/2 周二
 */
@Slf4j
@Component
@Aspect
public class CheckParamAspect {


    @Pointcut("execution( * com.example.demo.service..*(..)) && @annotation( com.example.demo.aop.ParamCheck)")
    public void pointcut() {
    }

    @Before(value = "pointcut()&& @annotation(check)", argNames= "jp,check")
    public void checkParam(JoinPoint jp, ParamCheck check) {
        log.info("对指定地方进行参数校验");
    }


}
