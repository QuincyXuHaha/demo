package com.example.demo.service.biz.pay.impl;

import com.example.demo.constant.EPayChannel;
import com.example.demo.service.biz.pay.IPayChannelService;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付通道实现
 *
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
@Service
public class AlipayServiceImpl implements IPayChannelService {
    @Override
    public void pay() {
        System.out.println("支付宝支付通道执行业务方法--------------------------");
    }

    @Override
    public EPayChannel getType() {
        return EPayChannel.ALIPAY;
    }
}
