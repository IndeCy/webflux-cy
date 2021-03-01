package com.lastcy.learn.webflux.webfluxcy.composeAndTransform;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Function;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.composeAndTransform
 * @Author: chenyang
 * @Date: 2021/2/8
 * @Version: 1.0
 */
@Slf4j
public class ComposeT {

    private static final Random rand = new Random();

    //新版本已经没有compose这个api了
    public static void main(String[] args) {
        Function<Flux<String>, Flux<String>> composeF = stream -> {
            if (rand.nextBoolean()) {
                return stream.doOnNext(s -> log.info("Path A:{}", s));
            }
            return stream.doOnNext(s -> log.info("Path B:{}", s));
        };


        Flux.range(100,10).log().map(i -> i+"").transform(composeF).subscribe(s -> log.info("value:{}",s));
    }

}
