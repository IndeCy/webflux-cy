package com.lastcy.learn.webflux.webfluxcy.suscriber;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
public class SubscriberTest {

    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(new CustomSubscriber2<>());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
