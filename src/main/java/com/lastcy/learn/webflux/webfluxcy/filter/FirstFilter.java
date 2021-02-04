package com.lastcy.learn.webflux.webfluxcy.filter;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.filter
 * @Author: chenyang
 * @Date: 2021/1/30
 * @Version: 1.0
 */
@Component
public class FirstFilter implements WebFilter {

    ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "xxx");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = exchange.getRequest();
        return DataBufferUtils.join(request.getBody()).map(dataBuffer -> {
            exchange.getAttributes().put("cachedRequestBody", dataBuffer);
            ServerHttpRequest decorator = new ServerHttpRequestDecorator(request) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return Mono.<DataBuffer>fromSupplier(() -> {
                        if (exchange.getAttributeOrDefault("cachedRequestBody", null) == null) {
                            // probably == downstream closed
                            return null;
                        }

                        // reset position
                        dataBuffer.readPosition(0);

                        // deal with Netty
                        NettyDataBuffer pdb = (NettyDataBuffer) dataBuffer;
                        return pdb.factory().wrap(pdb.getNativeBuffer().retainedSlice());
                    }).flux();
                }
            };

            // TODO 消费 dataBuffer，例如计算 dataBuffer 的哈希值并验证
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            try {
                dataBuffer.asInputStream().read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(new String(bytes));

//            threadLocal.set("aaa");
            return decorator;
        })
                .switchIfEmpty(Mono.just(request))
                .flatMap(req -> webFilterChain.filter(exchange.mutate().request(req).build()))
                .doOnEach(t ->{
                        threadLocal.set("aaa");
                    System.out.println(Thread.currentThread().getName() + " val:  " +threadLocal.get());})
                .publishOn(Schedulers.single())
                .doOnNext(t -> System.out.println(Thread.currentThread().getName() + " val:  " + threadLocal.get()))
                .doFinally(s -> {
                    DataBuffer dataBuffer = exchange.getAttributeOrDefault("cachedRequestBody", null);
                    if (dataBuffer != null) {
                        DataBufferUtils.release(dataBuffer);
                    }
                });
    }
}
