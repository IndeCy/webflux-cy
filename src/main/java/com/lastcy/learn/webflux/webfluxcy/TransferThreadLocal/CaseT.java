package com.lastcy.learn.webflux.webfluxcy.TransferThreadLocal;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.TransferThreadLocal
 * @Author: chenyang
 * @Date: 2021/2/9
 * @Version: 1.0
 */
@Slf4j
public class CaseT {

    public static void main(String[] args) {
        methodCase2();
//        methodCase();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void methodCase(){
        Flux.range(0,10)
//                .subscriberContext(context -> context.put("randoms",new HashMap<Object,Object>()))
                .flatMap(k -> Mono.subscriberContext()
                        .doOnNext(context -> {
                            Map<Object, Object> randoms = context.get("randoms");
                            log.info("put thread : {}, i: {}",Thread.currentThread().getName(),k);
                            randoms.put(k,new Random(k).nextGaussian());
                        })
                        .thenReturn(k))
                .publishOn(Schedulers.parallel())
                .flatMap(k ->
                        Mono.subscriberContext().map(context -> {
                            Map<Object,Object> map = context.get("randoms");
                            log.info("get thread : {}, i: {}",Thread.currentThread().getName(),k);
                            return map.get(k);
                        })
                )
                //todo 线程传递的context初始化比较特殊 它是必须从下游初始化去影响上游
                .subscriberContext(context -> context.put("randoms",new HashMap<Object,Object>()))
                .blockLast();


    }

    static void methodCase2(){
        Flux<Object> objectFlux = Flux.range(0, 10)
//                .subscriberContext(context -> context.put("randoms",new HashMap<Object,Object>()))
                .flatMap(k -> Mono.deferContextual(context -> {
                    Map<Object, Object> randoms = context.get("randoms");
                    double value = new Random(k).nextGaussian();
                    log.info("put thread : {}, k:{}, value: {}", Thread.currentThread().getName(),k, value);
                    randoms.put(k, value);
                    return Mono.empty();
                }).thenReturn(k))
                .publishOn(Schedulers.parallel())
                .flatMap(k ->
                        Mono.deferContextual(context -> {
                                    Map<Object, Object> map = context.get("randoms");
                                    Object data = map.get(k);
                                    log.info("get thread : {}, k: {}, data: {}", Thread.currentThread().getName(),k, data);
                                    return Mono.just(data);
                                }
                        ))
                //todo 线程传递的context初始化比较特殊 它是必须从下游初始化去影响上游
                //这是新款api
                .contextWrite(context -> context.put("randoms", new HashMap<Object, Object>()));

        objectFlux.subscribe();
        objectFlux.subscribe();

//                .blockLast();


    }
}
