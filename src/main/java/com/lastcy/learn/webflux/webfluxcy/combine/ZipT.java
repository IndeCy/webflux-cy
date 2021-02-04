package com.lastcy.learn.webflux.webfluxcy.combine;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.combine
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class ZipT {
    public static void main(String[] args) {
        Flux<Tuple3<Integer, Integer, Integer>> zip = Flux.zip(Flux.range(1, 3), Flux.range(4, 3), Flux.range(6, 2));
        zip.subscribe(t -> log.info("t1 {},t2 {},t3 {}",t.getT1(),t.getT2(),t.getT3()));
    }
}
