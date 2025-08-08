package com.heima.trade.listener;

import com.heima.api.client.OrderClient;
import com.heima.trade.domain.po.Order;
import com.heima.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayStatusListener {
    private final IOrderService orderService;

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "trade.pay.success.queue", durable = "true"),
            exchange = @Exchange(name = "pay.direct"),
            key = "pay.success"
    )})
    public void listenPaySuccess(Long id) {
        Order order = orderService.getById(id);
        if (order == null || order.getStatus() != 1) return;
        orderService.markOrderPaySuccess(id);
    }
}
