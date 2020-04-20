package com.alzz.demo.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @ClassName ForkJoinCalculate
 * @Description TODO
 * @Author lzx
 * @Date 2020/3/25 17:04
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    static final Long THRESHOLD = 100000L;

    public ForkJoinCalculate(Long start, Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end - start;
        if(length <= THRESHOLD){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            Long middle = (end - start) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork(); //拆分子线程 同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
            right.fork(); //拆分子线程 同时压入线程队列
            return left.join() + right.join();
        }
    }
}
