package com.lastcy.learn.webflux.webfluxcy.map;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

@Slf4j
public class MapT {

    private static final Random random = new Random();

    public static Flux<String> requestBooks(String user) {
        return Flux.range(1, random.nextInt(3) + 1)
                .map(i -> "book-" + i)
                .delayElements(Duration.ofMillis(3));
    }

    /**
     * concatMap 类似于concat必须前一个完成 后一个才开始订阅
     * flatMap 组合生成的Flux无序
     * flatMapSequential 组合的Flux按顺序
     * @param args
     */
    public static void main(String[] args) {
        Flux.just("user-1", "user-2", "user-3")
                .flatMapSequential(u -> requestBooks(u)
                        .map(b -> u + "/" + b))
                .subscribe(r -> log.info("onNext: {}", r));

//        requestBooks("me").subscribe(e -> log.info("book:{}",e));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
