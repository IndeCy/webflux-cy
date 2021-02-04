package com.lastcy.learn.webflux.webfluxcy.combine;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.combine
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class MergeT {
    public static void main(String[] args) {
        Flux<Integer> merge = Flux.merge(Flux.range(1, 3), Flux.range(4, 2), Flux.range(6, 5));
        merge.subscribe(e -> log.info("onNext: {},threadName {}", e, Thread.currentThread().getName()));
    }
}
