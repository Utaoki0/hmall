//package com.heima.filters;
//
//
//import cn.hutool.core.text.AntPathMatcher;
//import com.heima.config.AuthProperties;
//import com.heima.utils.JwtTool;
//import com.hmall.common.utils.CollUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class MyAuthGlobalFilter implements GlobalFilter, Ordered {
//
//    private final AuthProperties authProperties;
//    private final JwtTool jwtTool;
//    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //获取请求头
//        ServerHttpRequest request = exchange.getRequest();
//        System.err.println("request = " + request);
//        System.err.println(request.getURI());
//        System.err.println(request.getPath());
//        System.err.println(request.getURI().getPath());
//        //判断是否需要拦截
//        if (isExclude(exchange.getRequest().getURI().getPath())) {//排除
//            //放行
//            return chain.filter(exchange);
//        }
//        //获取token
//        List<String> headers = request.getHeaders().get("authorization");
//        String token = null;
//        if (!CollUtils.isEmpty(headers)) {
//            token = headers.get(0);
//        }
//        Long userId = null;
//        try {
//            userId = jwtTool.parseToken(token);
//        } catch (Exception e) {
//            //如果无效则拦截
//            ServerHttpResponse response = exchange.getResponse();
//            response.setRawStatusCode(401);
//            return response.setComplete();
//        }
//        //解析校验token
//
//        String userInfo = userId.toString();
//        ServerWebExchange swe = exchange.mutate()
//                .request(builder -> builder.header("user-info", userInfo))
//                .build();
//        System.out.println("userId = " + userId);
//        //放行
//        return chain.filter(swe);
//    }
//
//    private boolean isExclude(String path) {
//        for (String antPath : authProperties.getExcludePaths()) {
//            if (antPathMatcher.match(antPath, path))
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
