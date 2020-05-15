package com.example.demo.service.biz.captial;

import com.example.demo.constant.ECapitalInfo;
import com.example.demo.service.common.IStrategyService;

/**
 * 资方服务
 *
 * @author xuguangquan
 * @date 2020/5/15 周五
 */
public interface ICapitalService extends IStrategyService<ECapitalInfo> {


    void operateUser();

}
