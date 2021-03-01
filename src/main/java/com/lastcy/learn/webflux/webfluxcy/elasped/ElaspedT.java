package com.lastcy.learn.webflux.webfluxcy.elasped;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.elasped
 * @Author: chenyang
 * @Date: 2021/2/8
 * @Version: 1.0
 */
@Slf4j
public class ElaspedT {
    public static void main(String[] args) {
        Flux.range(0, 5)
                .delayElements(Duration.ofMillis(100))
                //把时间直接流逝掉
                .elapsed()
                .subscribe(e -> log.info("Elapsed {} ms: {}", e.getT1(), e.getT2()));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
