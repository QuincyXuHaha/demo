package com.example.demo.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author xuguangquan
 * @date 2020/6/2 周二
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ParamCheck {
}
