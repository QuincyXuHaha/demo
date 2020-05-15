package com.example.demo;

import com.example.demo.constant.ECapitalInfo;
import com.example.demo.constant.EPayChannel;
import com.example.demo.service.biz.captial.ICapitalService;
import com.example.demo.service.biz.pay.IPayChannelService;
import com.example.demo.service.common.StrategyContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication implements ApplicationListener<ApplicationStartedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ICapitalService capitalService = StrategyContainer.findServiceImplByType(ECapitalInfo.AIBANK, ICapitalService.class);
        IPayChannelService channelService = StrategyContainer.findServiceImplByType(EPayChannel.ALIPAY, IPayChannelService.class);
        System.out.println(capitalService);
        System.out.println(channelService);
        ICapitalService capitalService2 = StrategyContainer.findServiceImplByType(ECapitalInfo.AIBANK, ICapitalService.class);
        System.out.println(capitalService2);
    }
}
