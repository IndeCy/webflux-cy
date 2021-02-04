package com.lastcy.learn.webflux.webfluxcy.combine;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.combine
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class MergeT {
    public static void main(String[] args) {
        //由这里可以看出 三个流同时被订阅 而不用等待第一个流结束
        Flux<Long> merge = Flux.merge(Flux.interval(Duration.ofSeconds(1)).map(a -> a*2).doOnSubscribe(i -> System.out.println("sub 1")), Flux.interval(Duration.ofSeconds(1)).doOnSubscribe(i -> System.out.println("sub 2")), Flux.interval(Duration.ofSeconds(1)));
        merge.subscribe(e -> log.info("onNext: {},threadName {}", e, Thread.currentThread().getName()));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
