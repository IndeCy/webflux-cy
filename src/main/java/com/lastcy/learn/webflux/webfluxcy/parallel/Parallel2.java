package com.lastcy.learn.webflux.webfluxcy.parallel;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.parallel
 * @Author: chenyang
 * @Date: 2021/2/9
 * @Version: 1.0
 */
@Slf4j
public class Parallel2 {
    public static void main(String[] args) {
        Flux.range(1,100)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(s -> "user-" + s)
                //再返回成一个线程处理
                .sequential()
                .subscribe(s -> log.info("thread : {}, i: {}",Thread.currentThread().getName(),s));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
