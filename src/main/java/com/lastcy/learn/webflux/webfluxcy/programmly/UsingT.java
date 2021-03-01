package com.lastcy.learn.webflux.webfluxcy.programmly;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Random;

/**
 * @Package: com.lastcy.learn.webflux.webfluxcy.programmly
 * @Author: chenyang
 * @Date: 2021/2/7
 * @Version: 1.0
 */
@Slf4j
public class UsingT {

    public static void main(String[] args) {
//        normalMethod();
        nonBlockMethod();
    }

    static void normalMethod(){
        try (Connection conn = Connection.newConnection()){
            conn.getData().forEach(s -> log.info("normal, var {}",s));
        }catch (Exception e){
            log.error("error occurred",e);
        }
    }

    static void nonBlockMethod(){
        Flux<String> io = Flux.using(Connection::newConnection, connection -> Flux.fromIterable(connection.getData()), Connection::close);
        io.subscribe(s -> log.info("var:{}",s),throwable -> log.error("error occurred",throwable),()-> log.info("finished"));
    }


    static class Connection implements AutoCloseable{

        private final Random rnd = new Random();

        @Override
        public void close() {
            log.info("connect closed!");
        }

        public Iterable<String> getData(){
            if(rnd.nextInt(10) < 3)
                throw  new RuntimeException("exc");
            return Arrays.asList("some","data");
        }

        public static Connection newConnection(){
            log.info("newConnection created!");
            return new Connection();
        }
    }
}


