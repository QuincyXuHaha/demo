package com.example.demo.service.biz.pay;


import com.example.demo.constant.EPayChannel;
import com.example.demo.service.common.IStrategyService;

/**
 * 资方服务
 *
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
public interface IPayChannelService extends IStrategyService<EPayChannel> {

    void pay();

}
