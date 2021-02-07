package com.lastcy.learn.webflux.webfluxcy.sample;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.sample
 * @Author: chenyang
 * @Date: 2021/2/5
 * @Version: 1.0
 */
@Slf4j
public class SampleT {

    public static void main(String[] args) {

        Flux.range(1,1000)
                //延迟每秒发出一个元素
                .delayElements(Duration.ofSeconds(1))
                //每5秒一次取样
                .sample(Duration.ofSeconds(3))
                .subscribe(e -> log.info("onNext: {}", e));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
