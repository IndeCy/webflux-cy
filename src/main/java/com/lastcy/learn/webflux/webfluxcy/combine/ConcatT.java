package com.lastcy.learn.webflux.webfluxcy.combine;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.combine
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class ConcatT {

    public static void main(String[] args) throws InterruptedException {
//        Flux<Integer> concat = Flux.concat(Flux.range(1, 3).doOnSubscribe(a-> System.out.println("1 complete")), Flux.range(4, 2).doOnSubscribe(a-> System.out.println("2 complete")), Flux.range(6, 5).doOnSubscribe(a-> System.out.println("3 complete")));
        //由这里可以看出 第一个流不结束第二个流不会被订阅
        Flux<Long> concat = Flux.concat(Flux.interval(Duration.ofSeconds(1)).map(a -> a*2).doOnSubscribe(i -> System.out.println("sub 1")), Flux.interval(Duration.ofSeconds(1)).doOnSubscribe(i -> System.out.println("sub 2")), Flux.interval(Duration.ofSeconds(1)));

        concat.subscribe(e -> {
//            if (e % 100000 == 0)
                log.info("onNext: {},threadName {}", e, Thread.currentThread().getName());
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
