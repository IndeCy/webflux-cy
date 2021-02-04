package com.lastcy.learn.webflux.webfluxcy.batch;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.LinkedList;

@Slf4j
public class BatchT {

    public static void main(String[] args) {
//        buffer();
//        window();
        grouping();
    }


    static void buffer() {
        Flux.range(1, 10)
                .buffer(4)
                .subscribe(e -> log.info("onNext: {}", e));
    }

    static void window() {
//        Flux.range(101,20)
//                .window(4)
//                .subscribe(f -> log.info("e:{}",f));
        Flux<Flux<Integer>> fluxFlux = Flux.range(101, 20)
                //true跟false的区别是当前的判断点是归到前一个window还是后一个window ，true后 false前
                .windowUntil(BatchT::isPrime,true);

        fluxFlux.subscribe(
                e -> e.collectList().subscribe
                        (
                                a -> log.info("list:{}", a)
                        )
        );


    }

    static void grouping() {
        Flux.range(1, 10)
                .groupBy(e -> {
                    if (e % 2 == 0) {
                        return "even";
                    }
                    return "odd";
                })
                .subscribe(flux -> flux.scan(new LinkedList<>(),(list, ele) -> {
                    list.add(ele);
                    if(list.size() > 2){
                        list.remove(0);
                    }
                    return list;
                }).filter(arr -> arr.size() != 0).subscribe(arr2 -> log.info("arr: {}",arr2)));
    }

    public static boolean isPrime(Integer i) {
        return i % 2 == 0;
    }
}
