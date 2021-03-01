package com.lastcy.learn.webflux.webfluxcy.connect;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.connect
 * @Author: chenyang
 * @Date: 2021/2/7
 * @Version: 1.0
 */
public class ConnectT {

    public static void main(String[] args) {
        //当流是冷数据的时候，每次订阅都会重新初始化流的数据
        Flux<Integer> defer = Flux.defer(() -> Flux.range(1, 3)
                .doOnSubscribe(e -> System.out.println("onSubscribe")));

        defer.subscribe(e -> System.out.println(Thread.currentThread().getName() + ",sub1 : " + e));
        defer.subscribe(e -> System.out.println(Thread.currentThread().getName() + ",sub2 : " + e));

        //通过connectFlux来让流只初始化一次数据
        ConnectableFlux<Integer> publish = defer.publish();


        publish.subscribe(e -> System.out.println(Thread.currentThread().getName() + ",sub1 : " + e));
        publish.subscribe(e -> System.out.println(Thread.currentThread().getName() + ",sub2 : " + e));

        publish.connect();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
