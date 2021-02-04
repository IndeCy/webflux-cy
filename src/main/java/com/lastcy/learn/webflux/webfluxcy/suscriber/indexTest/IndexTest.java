package com.lastcy.learn.webflux.webfluxcy.suscriber.indexTest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber.indexTest
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
@Slf4j
public class IndexTest {

    public static void main(String[] args) throws Exception{

//        Flux.range(2021,5)
//                .timestamp()
//                .index()
//                .subscribe(e -> log.info("index {}, timestamp {}, value {}",
//                        e.getT1(),
//                        e.getT2().getT1(),
//                        e.getT2().getT2()));
//
//        System.in.read();
        any();
    }

    static void any(){
        Flux.just(3, 5, 7, 9, 11, 15, 16, 17)
                .any(e -> e % 2 == 0)
                .subscribe(hasEvens -> log.info("Has evens: {}", hasEvens));
    }

}
