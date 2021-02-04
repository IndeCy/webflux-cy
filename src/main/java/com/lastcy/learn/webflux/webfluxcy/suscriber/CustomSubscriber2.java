package com.lastcy.learn.webflux.webfluxcy.suscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
@Slf4j
public class CustomSubscriber2<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnNext(T value) {
//        requestUnbounded();
        System.out.println(value);
//        System.out.println("hook on next");
    }
}
