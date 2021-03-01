package com.lastcy.learn.webflux.webfluxcy.composeAndTransform;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Random;
import java.util.function.Function;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.composeAndTransform
 * @Author: chenyang
 * @Date: 2021/2/8
 * @Version: 1.0
 */
@Slf4j
public class TransformT {



    //复用这个indexUser的逻辑
    public static void main(String[] args) {
        Function<Flux<String>,Flux<String>> indexUser =
                stream -> stream.index().doOnNext(t -> log.info("[{}],user: {}",t.getT1(),t.getT2())).map(Tuple2::getT2);

        Flux<String> transform = Flux.range(1000, 3).map(i -> "user-" + i).transform(indexUser);
        transform.subscribe(s -> log.info("user:{}",s));
        transform.subscribe(s -> log.info("user:{}",s));




    }
}
