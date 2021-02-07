package com.lastcy.learn.webflux.webfluxcy.programmly;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.stream.IntStream;

@Slf4j
public class PushT {

    public static void main(String[] args) {

        Scheduler single = Schedulers.single();
        //从一个单线程资源创建流，同时可以自定义背压
        Flux.push(emitter -> {
            IntStream.range(1000,2000)
                    .forEach(emitter::next);
        }).delayElements(Duration.ofMillis(1))
                .publishOn(single).subscribe(e -> log.info("onNext: {}", e));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
