package com.lastcy.learn.webflux.webfluxcy.suscriber.startstopTest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber.startstopTest
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        Mono<String> a = Mono.defer(() -> Mono.just("a"));
        Mono<String> b = Mono.defer(() -> Mono.just("b"));
//        Mono<String> end = end();
        Flux<String> map = Flux.interval(Duration.ofSeconds(1)).map(String::valueOf);

        map.subscribe(s -> log.info("subscribe {},value {}",Thread.currentThread().getName(),s));
//        map.subscribe(s -> log.info("threadName {},value {}",Thread.currentThread().getName(),s));

        //todo 为什么跳过和停止条件不行呢？
        map.skipUntilOther(a)
                .takeUntilOther(b)
                .subscribe(s -> log.info("skip {},value {}",Thread.currentThread().getName(),s));

        map.skip(5).take(5).subscribe(s -> log.info("skip5 {},value {}",Thread.currentThread().getName(),s));


//        map.subscribe(System.out::println);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        a.subscribe(System.out::println);


        try {
            Thread.sleep(5000);
            b.subscribe(System.out::println);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    static Mono<String> start() {
        return Mono.just("start");
    }

    static Mono<String> end() {
        return Mono.just("end");
    }
}
