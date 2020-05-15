package com.example.demo.service.common;

/**
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
public interface IStrategyService<T extends Enum<T>> {

    /**
     * 获取策略key
     *
     * @return 策略key
     */
    T getType();


}
