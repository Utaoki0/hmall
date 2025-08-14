package com.hmall.trade;

import com.hmall.api.config.FeignLogConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.hmall.trade.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hmall.api.client",
        defaultConfiguration = FeignLogConfig.class)
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }



}