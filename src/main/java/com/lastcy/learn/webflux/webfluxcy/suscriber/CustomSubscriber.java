package com.lastcy.learn.webflux.webfluxcy.suscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.suscriber
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
@Slf4j
public class CustomSubscriber<T> implements Subscriber<T> {

    volatile Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        log.info("on subscribe");
        subscription.request(1);
    }

    @Override
    public void onNext(T t) {
        log.info("onNext {}",t.toString());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("onError",throwable);
    }

    @Override
    public void onComplete() {
        log.info("complete!");
    }
}
