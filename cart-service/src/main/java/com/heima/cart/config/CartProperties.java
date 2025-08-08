package com.heima.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hm.cart")
@Data
@Component
public class CartProperties {
    private Integer maxItems;
}
