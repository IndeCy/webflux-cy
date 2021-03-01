package com.lastcy.learn.webflux.webfluxcy.backpressure;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.backpressure
 * @Author: chenyang
 * @Date: 2021/2/7
 * @Version: 1.0
 */
public class BackpressureT {
    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.range(1, 10000)
                .delayElements(Duration.ofMillis(1));

//        integerFlux.limitRequest(10).subscribe(System.out::println,Throwable::printStackTrace,()-> System.out.println(Thread.currentThread().getName() + "10 complete"));
//        integerFlux.limitRequest(100).subscribe(System.out::println,Throwable::printStackTrace,()-> System.out.println(Thread.currentThread().getName() + "100 complete"));
        integerFlux.onBackpressureBuffer(5).subscribe(s -> {
            try {
                Thread.sleep(100);
                System.out.println(s);
            } catch (InterruptedException e) {
                //ignore
            }
        },Throwable::printStackTrace,()-> System.out.println(Thread.currentThread().getName() + "100 complete"));



        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
