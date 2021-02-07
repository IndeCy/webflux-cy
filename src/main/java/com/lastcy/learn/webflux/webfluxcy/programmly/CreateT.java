package com.lastcy.learn.webflux.webfluxcy.programmly;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CreateT {
    public static void main(String[] args) {
        //相比于push  create从多线程创建流
        Flux.create(emitter -> {
            emitter.onDispose(() -> log.info("Disposed"));
            // push events to emitter
            emitter.next(1);
            emitter.next(2);
            emitter.next(3);
            emitter.next(4);
            emitter.next(5);
            emitter.next(6);
            emitter.complete();
        }).doOnComplete(() -> log.info("complete"))
                .subscribe(e -> log.info("onNext: {}", e)).dispose();
    }
}
