package com.xss.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @ClassName FluxRouter
 * @Description 配置类
 * @Author xushaoshuai
 * @Date 2021/1/7 2:30 下午
 */

@Configuration
public class FluxRouter {

    @Bean
    public RouterFunction<ServerResponse> routeFlux(FluxHandller fluxHandller){
        return
                RouterFunctions
                        .route(RequestPredicates.path("/001")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                                serverRequest -> ServerResponse.ok().body(BodyInserters.fromValue("xxoo")))

                        .andRoute(RequestPredicates.path("/002"),fluxHandller::getXxx)

                        .andRoute(RequestPredicates.path("/003"),fluxHandller::getObj)

                        .andRoute(RequestPredicates.path("/004"),fluxHandller::getParams)

                        .andRoute(RequestPredicates.path("/005/{id}_{name}"),fluxHandller::getPath);
    }
}
