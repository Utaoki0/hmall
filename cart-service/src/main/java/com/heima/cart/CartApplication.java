package com.heima.cart;

import com.heima.api.client.ItemClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients(basePackages = "com.heima.api.client")
@EnableFeignClients(clients = {ItemClient.class})
@SpringBootApplication(scanBasePackages = {"com.heima.api"})
@MapperScan("com.heima.cart.mapper")
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}