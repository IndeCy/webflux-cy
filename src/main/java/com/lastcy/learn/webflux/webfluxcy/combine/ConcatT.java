package com.lastcy.learn.webflux.webfluxcy.combine;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.combine
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class ConcatT {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> concat = Flux.concat(Flux.range(1, 3), Flux.range(4, 2), Flux.range(6, 5)
        );
        concat.subscribe(e -> {
//            if (e % 100000 == 0)
                log.info("onNext: {},threadName {}", e, Thread.currentThread().getName());
        });
//        Thread.sleep(1000);
//        concat.subscribe(e -> {
//            if (e % 100000 == 0)
//                log.info("onNext: {}", e);
//
//        });
//        System.out.println(Thread.currentThread().getName());

//        Flux.range(1,Integer.MAX_VALUE).subscribe(System.out::println);
    }

}
