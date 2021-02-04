package com.lastcy.learn.webflux.webfluxcy.controller;

import com.lastcy.learn.webflux.webfluxcy.support.Person;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.controller
 * @Author: chenyang
 * @Date: 2021/1/30
 * @Version: 1.0
 */
@RestController
@RequestMapping("/first")
public class FirstController {

    @GetMapping
    public Mono<String> firstGet(){
        return Mono.just("hello webflux!");
    }

    @PostMapping
    public Flux<Person> firstPost(@RequestBody Person person){
        return Flux.just(person);
    }

}
