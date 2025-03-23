package com.heima.api.config;


import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoInterceptor() {
        return requestTemplate -> {
            Long user = UserContext.getUser();
            requestTemplate.header("userId", user.toString());
        };
    }
}
