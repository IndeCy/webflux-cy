package com.lastcy.learn.webflux.webfluxcy.block;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BlockT {

    public static void main(String[] args) {
        Iterable<Integer> integers = Flux.defer(() -> {
            return Flux.range(1, 10);
        }).publishOn(Schedulers.single()).toIterable();

        integers.forEach(System.out::println);
    }
}
