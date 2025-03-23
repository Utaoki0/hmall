package com.heima.filters;

import cn.hutool.core.text.AntPathMatcher;
import com.heima.config.AuthProperties;
import com.heima.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final JwtTool jwtTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求头
        ServerHttpRequest request = exchange.getRequest();
        //2.判断是否需要拦截
        if (isExclude(request.getPath().toString()))
            return chain.filter(exchange);//不需要拦截，直接放行
        //3.获取token
        List<String> header = request.getHeaders().get("authorization");
        String token = null;
        if (header != null && !header.isEmpty()) {
            token = header.get(0);
        }
        //4.解析校验token
        Long userId;
        try {
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
            //无效，设置响应状态码
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //5.传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        System.out.println("userId = " + userId);
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        for (String exPath : authProperties.getExcludePaths())
            if (antPathMatcher.match(exPath, path))
                return true;
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
