//package com.lastcy.learn.webflux.webfluxcy.controller;
//
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.HandlerFunction;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.reactive.function.server.RouterFunctions.*;
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//
///**
// * @Package: com.lastcy.learn.webflux.webfluxcy.controller
// * @Author: chenyang
// * @Date: 2021/2/19
// * @Version: 1.0
// */
//@Component
//public class SecondRouter implements RouterFunction<ServerResponse> {
//    @Override
//    public Mono<HandlerFunction<ServerResponse>> route(ServerRequest serverRequest) {
//        return null;
//    }
//
//
////    @Override
////    public Mono<HandlerFunction<ServerResponse>> route(ServerRequest serverRequest) {
////        return nest(path("/orders"),
////                nest(accept(APPLICATION_JSON),
////
////                ); }
////}
////    route(GET("/{id}"), handler::get) .andRoute(method(HttpMethod.GET), handler::list)
////        ) // .andNest(contentType(APPLICATION_JSON), // route(POST("/"), handler::create) // )
////    }
//}
