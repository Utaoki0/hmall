package com.heima.api.client.fallback;


import com.heima.api.client.ItemClient;
import com.heima.api.dto.ItemDTO;
import com.heima.api.dto.OrderDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ItemClientFallBack implements FallbackFactory<ItemClient> {

    @Override
    public ItemClient create(Throwable cause) {
        return new ItemClient() {
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                log.error("查询商品信息失败", cause);
                return Collections.emptyList();
            }

            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                //不清楚具体做什么处理 由调用者处理
                throw new RuntimeException("扣减库存失败");
            }
        };
    }
}
