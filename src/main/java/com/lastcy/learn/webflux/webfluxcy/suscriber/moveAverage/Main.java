package com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage
 * @Author: chenyang
 * @Date: 2021/2/3
 * @Version: 1.0
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        int bucketSize = 5;
        Flux.range(1, 500)
                .index()
                .scan(
                        new int[bucketSize],
                        (acc, elem) -> {
                            acc[(int) (elem.getT1() % bucketSize)] = elem.getT2();
                            return acc;
                        })
                .skip(bucketSize)
                .map(array -> Arrays.stream(array).sum() * 1.0 / bucketSize).subscribe(av -> log.info("Running average: {}", av));
    }
}
