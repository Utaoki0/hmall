//package com.hmall.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class MyGlobalFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //todo 模拟登陆校验
//
//        //1.获取登陆凭证
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        //打印
//        System.err.println("headers"+headers);
//        //放行
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        //配置过滤器执行顺序，越小优先级越高
//        return 0;
//    }
//}
