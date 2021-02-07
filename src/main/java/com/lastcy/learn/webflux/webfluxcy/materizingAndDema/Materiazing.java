package com.lastcy.learn.webflux.webfluxcy.materizingAndDema;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Materiazing {

    public static void main(String[] args) {
        Flux.range(1,3)
                .doOnNext(s -> log.info("val:{}",s))
                //转化为signal
                .materialize()
                .doOnNext(s -> log.info("sig:{}",s))
                //再转化为值
                .dematerialize()
                .collectList()
                .subscribe(System.out::println);
    }
}
