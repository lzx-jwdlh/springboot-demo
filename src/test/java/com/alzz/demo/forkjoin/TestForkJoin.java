package com.alzz.demo.forkjoin;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @ClassName TestForkJoin
 * @Description TODO
 * @Author lzx
 * @Date 2020/3/25 17:12
 */
public class TestForkJoin {

    public static void main(String[] args) {
        Instant now = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> longForkJoinCalculate = new ForkJoinCalculate(0L, 10000L);
        Long invoke = forkJoinPool.invoke(longForkJoinCalculate);
        System.out.println(invoke);
        Instant end = Instant.now();
        System.out.println(Duration.between(end,now));
    }

    @Test
    public void test2(){
        long reduce = LongStream.rangeClosed(0, 10000000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
    }
}
