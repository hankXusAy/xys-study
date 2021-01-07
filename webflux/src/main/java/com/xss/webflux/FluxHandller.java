package com.xss.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName FluxHandller
 * @Description
 * @Author xushaoshuai
 * @Parameters
 * @Date 2021/1/7 2:28 下午
 * @Return
 */
@Component
public class FluxHandller {

    public Mono<ServerResponse> getXxx(ServerRequest serverRequest) {

        //业务逻辑

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("么么哒~"));
    }

    public Mono<ServerResponse> getParams(ServerRequest serverRequest) {

        Optional<String> id = serverRequest.queryParam("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(id));
    }

    public Mono<ServerResponse> getObj(ServerRequest serverRequest) {
        Person person = new Person(1,"张三");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(person));
    }

    public Mono<ServerResponse> getPath(ServerRequest serverRequest) {
        Map<String, String> map = serverRequest.pathVariables();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(map));
    }
}
