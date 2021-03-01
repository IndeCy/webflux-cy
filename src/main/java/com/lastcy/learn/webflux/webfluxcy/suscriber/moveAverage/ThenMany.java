package com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber.moveAverage
 * @Author: chenyang
 * @Date: 2021/2/4
 * @Version: 1.0
 */
@Slf4j
public class ThenMany {
    public static void main(String[] args) {


        Integer[] integers = new Integer[10];
        Arrays.fill(integers,0);
        Test origin = new Test("origin");

        /**
         * thenMany结束前一个流 开始新的流
         * 在这里只会打印4，5
         */
//        Flux.just(1, 2, 3)
//                .thenMany(Flux.just(4, 5))
//                .subscribe(e -> log.info("onNext: {}", e));

//        Flux.just(1, 2, 3)
//                .then()
//                .subscribe(e -> log.info("onNext: {}", e), Throwable::printStackTrace,() -> System.out.println("aaa"));

//        Mono<Test> mono1 = Mono.just(origin);
//        Mono<Test> mono2 = Mono.just(origin);
//        mono1.doOnNext(s -> s.setName("first")).subscribe(s -> System.out.println(s.getName()));
//        mono2.subscribe(s -> System.out.println(s.getName()));
        //            integers[(s.getT1()).intValue()] = 1;
        //todo 为什么（doOnNext,doOnEach）是从第二个元素开始变成1,而doOnSubscribe达到了我的效果
        //todo 推测是因为这两者都是第一个元素发出后再发出信号
        Flux.fromArray(integers).doOnNext(s -> Arrays.fill(integers,1)).subscribe(System.out::print);
        System.out.println();
        Flux.fromArray(integers).index().doOnNext(s -> integers[(s.getT1()).intValue()] = 2)
                .thenMany(Flux.fromArray(integers))
                .subscribe(System.out::print);
        System.out.println();
        Flux.fromArray(integers).doOnNext(System.out::print).subscribe();
        System.out.println();
        Flux.fromArray(integers).subscribe(System.out::print);
    }

    static class Test{
        private String name;

        public Test(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
