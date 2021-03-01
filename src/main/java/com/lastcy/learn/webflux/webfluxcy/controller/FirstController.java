package com.lastcy.learn.webflux.webfluxcy.controller;

import com.lastcy.learn.webflux.webfluxcy.support.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.controller
 * @Author: chenyang
 * @Date: 2021/1/30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/first")
public class FirstController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public Mono<String> firstGet(){
        Object forObject = restTemplate.getForObject(URI.create("http://xxx/aaa"), Object.class);
        return Mono.just("hello webflux!");
    }

    @PostMapping
    public Flux<Person> firstPost(@RequestBody Person person){
        return Flux.just(person);
    }

}
