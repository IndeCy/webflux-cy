package com.lastcy.learn.webflux.webfluxcy.cache;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.cache
 * @Author: chenyang
 * @Date: 2021/2/7
 * @Version: 1.0
 */
@Slf4j
public class CacheT {
    public static void main(String[] args) {
        Flux<Integer> source = Flux.range(0, 2) .doOnSubscribe(s ->
                log.info("new subscription for the cold publisher"));
        Flux<Integer> cachedSource = source.cache(Duration.ofSeconds(1));
        //只会打印一次doOnSubscribe信息，因为第二次使用了第一次的缓存
        cachedSource.subscribe(e -> log.info("[S 1] onNext: {}", e));
        cachedSource.subscribe(e -> log.info("[S 2] onNext: {}", e));
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //再次打印一次doOnSubscribe的信息，因为cache过期了
        cachedSource.subscribe(e -> log.info("[S 3] onNext: {}", e));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
