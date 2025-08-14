package com.hmall.cart;

import com.hmall.api.client.ItemClient;
import com.hmall.api.config.FeignLogConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.hmall.cart.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hmall.api.client",
        defaultConfiguration = FeignLogConfig.class)
//@EnableFeignClients(clients = {ItemClient.class})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}