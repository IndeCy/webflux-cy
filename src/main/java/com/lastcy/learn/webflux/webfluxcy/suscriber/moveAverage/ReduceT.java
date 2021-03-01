package com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage
 * @Author: chenyang
 * @Date: 2021/2/3
 * @Version: 1.0
 */
@Slf4j
public class ReduceT {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .reduce(0, (acc, elem) -> acc + elem) .subscribe(result -> log.info("Result: {}", result));


        Flux.range(1, 5)
                .scan(new int[5], (acc, elem) -> {acc[elem%5] = acc[elem%5] + elem;return acc;})
                .subscribe(result -> log.info("Result: {}", result));

    }
}
