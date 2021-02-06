package com.lastcy.learn.webflux.webfluxcy.peeking;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class PeekT {

    /**
     * doOnNext,doOnComplete,doOnError,doOnRequest,doOnEach
     * @param args
     */

    public static void main(String[] args) {
        Flux.just(1, 2, 3)
                .concatWith(Flux.error(new RuntimeException("Conn error")))
                .doOnEach(s -> log.info("signal: {}", s))
                .subscribe();
    }
}
