package com.lastcy.learn.webflux.webfluxcy.programmly;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import javax.persistence.Tuple;
import java.time.Duration;

@Slf4j
public class Generate {

    public static void main(String[] args) {
//        Flux.generate(
//                ()-> Tuples.of(0L,1L),
//                (state,sink) -> {
//                    log.info("generated value: {}", state.getT2()); //
//                    sink.next(state.getT2()); // (1.2)
//                    long newValue = state.getT1() + state.getT2(); //
//                    return Tuples.of(state.getT2(), newValue);
//                }).delayElements(Duration.ofMillis(1))
//                .take(7)
//                .subscribe(e -> log.info("onNext: {}", e));
        //fixme 相同代码跟书上的输出不一样
        Flux.generate( // (1)
                () -> Tuples.of(0L, 1L), // (1.1)
                (state, sink) -> { //
                    log.info("generated value: {}", state.getT2()); //
                    sink.next(state.getT2()); // (1.2)
                    long newValue = state.getT1() + state.getT2(); //
                    return Tuples.of(state.getT2(), newValue); // (1.3)
                })
                .delayElements(Duration.ofMillis(1)) // (2)
                .take(7) // (3)
                .subscribe(e -> log.info("onNext: {}", e)); // (4)



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
