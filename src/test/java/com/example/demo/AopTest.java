package com.example.demo;

import com.example.demo.service.biz.captial.ICapitalService;
import org.aspectj.lang.annotation.Around;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author xuguangquan
 * @date 2020/6/2 周二
 */
@SpringBootTest
public class AopTest {

    @Resource
    ICapitalService aiBankServiceImpl;

    @Test
    public void test() {
        aiBankServiceImpl.operateUser();
    }

}
