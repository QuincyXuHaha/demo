package com.example.demo.service.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 策略服务容器
 *
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
@Slf4j
@Component
public class StrategyContainer implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static Lock lock = new ReentrantLock();

    private static final Map<Class<?>, Map<Enum<?>, IStrategyService<?>>> CONTAINER = new ConcurrentHashMap<>(8);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        StrategyContainer.applicationContext = applicationContext;
    }

    /**
     * 获取具体实现
     *
     * @param <T>   实现策略泛型
     * @param <S>   实现接口
     * @param type  实现策略
     * @param clazz 实现接口
     * @return 具体实现
     */
    public static <T extends Enum<T>, S extends IStrategyService<T>> S findServiceImplByType(T type, Class<S> clazz) {
        Assert.notNull(type, "策略key不能为空");
        Assert.notNull(clazz, "接口类型不能为空");
        // 先看缓存容器里面有没有，没有的话去spring容器获取然后缓存下来
        IStrategyService<T> service = findServiceImplByCache(type, clazz);
        if (service == null) {
            lock.lock();
            try {
                if (CONTAINER.containsKey(clazz)) {
                    return findServiceImplByCache(type, clazz);
                }
                //缓存该类的所以实现
                return cacheServiceByClass(type, clazz);
            } finally {
                lock.unlock();
            }
        }
        return (S) service;
    }

    private static <T extends Enum<T>, S extends IStrategyService<T>> S findServiceImplByCache(T type, Class<S> clazz) {
        if (CONTAINER.containsKey(clazz)) {
            Map<Enum<?>, IStrategyService<?>> strategyServiceMap = CONTAINER.get(clazz);
            if (strategyServiceMap != null) {
                return (S) strategyServiceMap.get(type);
            }
        }
        return null;
    }

    private static <T extends Enum<T>, S extends IStrategyService<?>> S cacheServiceByClass(T type, Class<S> clazz) {
        Map<String, S> beans = applicationContext.getBeansOfType(clazz);
        Map<Enum<?>, IStrategyService<?>> serviceMap = new HashMap<>(16);
        CONTAINER.put(clazz, serviceMap);
        Set<Map.Entry<String, S>> entries = beans.entrySet();
        for (Map.Entry<String, S> entry : entries) {
            if (serviceMap.containsKey(type)) {
                // 重复实现接口了
                log.error("{}接口存在多个相同的{}类型实现，请检查", clazz.getSimpleName(), type.name());
                return null;
            }
            serviceMap.put(entry.getValue().getType(), entry.getValue());
        }
        return (S) serviceMap.get(type);
    }

}
