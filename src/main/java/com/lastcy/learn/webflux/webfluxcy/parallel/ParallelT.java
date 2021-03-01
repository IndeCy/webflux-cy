package com.lastcy.learn.webflux.webfluxcy.parallel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.parallel
 * @Author: chenyang
 * @Date: 2021/2/9
 * @Version: 1.0
 */
@Slf4j
public class ParallelT {
    public static void main(String[] args) {
        Flux.range(1,1)
                .doOnNext(i -> log.info("before publish on thread : {}, i: {}",Thread.currentThread().getName(),i))
                //publishOn可以影响后面的操作包括subscribe
                .publishOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
                .doOnNext(i -> log.info("after publish on thread : {}, i: {}",Thread.currentThread().getName(),i))
                //subscribeOn不受publishOn的影响
                .subscribeOn(Schedulers.single())
                .subscribe(i ->log.info("subscribe thread : {}, i: {}",Thread.currentThread().getName(),i));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
