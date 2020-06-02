package com.example.demo.service.biz.captial.impl;

import com.example.demo.aop.ParamCheck;
import com.example.demo.constant.ECapitalInfo;
import com.example.demo.service.biz.captial.ICapitalService;
import org.springframework.stereotype.Service;


/**
 * 百信银行资方实现
 *
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
@Service
public class AiBankServiceImpl implements ICapitalService {
    @ParamCheck
    @Override
    public void operateUser() {
        System.out.println("百信银行资方执行业务方法--------------------------");
    }

    @Override
    public ECapitalInfo getType() {
        return ECapitalInfo.AIBANK;
    }
}
