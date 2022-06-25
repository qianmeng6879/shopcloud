package top.mxzero.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Flux;
import top.mxzero.jwt.config.JWTConfigProperties;
import top.mxzero.jwt.service.ITokenService;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Configuration
@Slf4j
public class DefaultGlobalFilter {
    private static final String TOKEN_NAME = "token";
    private static final String UNAUTHORIZED_MESSAGE = "{\"status\":401,\"message\":\"身份信息无效\"}";

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private JWTConfigProperties jwtConfigProperties;

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Bean
    @Order(-10)
    public GlobalFilter getSecondFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            log.info("{} {} {}", request.getRemoteAddress(), request.getMethod(), request.getPath());


            for (String path : jwtConfigProperties.getSkips()) {
                if (antPathMatcher.match(path, request.getPath().toString())) {
                    return chain.filter(exchange);
                }
            }

            String token;

            try {
                token = Objects.requireNonNull(request.getHeaders().get(TOKEN_NAME)).get(0);
            } catch (Exception ignored) {
                token = request.getQueryParams().getFirst(TOKEN_NAME);
            }

            if (!tokenService.verifyToken(token)) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().set("content-type", "application/json");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = response.bufferFactory().wrap(UNAUTHORIZED_MESSAGE.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Flux.just(buffer));
            }

            return chain.filter(exchange);
        };
    }

}
