package com.lastcy.learn.webflux.webfluxcy.handleError;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.handleError
 * @Author: chenyang
 * @Date: 2021/2/7
 * @Version: 1.0
 */
@Slf4j
public class HandleErrorT {
    private static final Random random = new Random();

    public static void main(String[] args) {
//        recommendedBooks("cy").subscribe(s -> log.info("value:{}",s));
        implementErrorsHandle();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Flux<String> recommendedBooks(String userId) {
        return Flux.defer(() -> {

            if (random.nextInt(10) < 7) {
                return Flux.<String>error(new RuntimeException("Err"))
                        .delaySequence(Duration.ofMillis(100));
            } else {
                return Flux.just("Blue Mars", "The Expanse").delayElements(Duration.ofMillis(50));
            }
        }).doOnSubscribe(s -> log.info("Request for {}", userId));
    }

    static void implementErrorsHandle() {
        Flux.just("user-1")
                .flatMap(user -> recommendedBooks(user)
                        //最大重试5次,初次延迟100ms
                        .retryWhen(Retry.backoff(5, Duration.ofMillis(100)))
                        //总超时时间不超过400ms
                        .timeout(Duration.ofMillis(400))
                        //发生异常就返回一个预设值
                        .onErrorResume(e -> Flux.just("luxun"))).subscribe(e -> log.info("onNext :{}", e), e -> log.warn("onError : {}", e.getMessage()), () -> log.info("onComplete"));
    }
}
