package com.alzz.demo.core.systemlog;

import com.alzz.demo.domain.SystemLog;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SystemLogQueue
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/15 15:16
 */
@Component
public class SystemLogQueue {

    private BlockingQueue<SystemLog> blockingQueue = new LinkedBlockingQueue<>();

    public void add(SystemLog systemLog) {
        blockingQueue.add(systemLog);
    }

    public SystemLog poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<SystemLog> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.add(new SystemLog());
        System.out.println(1);
        SystemLog poll = blockingQueue.poll(50, TimeUnit.SECONDS);
        blockingQueue.add(new SystemLog());
        System.out.println(1);
    }

}
