package com.lastcy.learn.webflux.webfluxcy.defer;

import reactor.core.publisher.Mono;

import java.util.Scanner;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.defer
 * @Author: chenyang
 * @Date: 2021/2/2
 * @Version: 1.0
 */
public class DeferTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s.equals("end")){
                break;
            }
            test1(s);
            test2(s);
        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * defer不会立即执行，必须事subscribe之后执行
     * @param flag
     * @return
     */
    public static Mono<Integer> test1(String flag){
        return Mono.defer(() -> {
            System.out.println("flag: "+ flag);
           return flag.equals("1") ? Mono.just(1) : Mono.just(2);
        });
    }

    /**
     * 这种在初始化的时候就直接执行了
     * @param flag
     * @return
     */
    public static Mono<Integer> test2(String flag){
        System.out.println("test2 flag: "+ flag);
        return flag.equals("1") ? Mono.just(1) : Mono.just(2);

    }

}
