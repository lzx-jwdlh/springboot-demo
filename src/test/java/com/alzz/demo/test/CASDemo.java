package com.alzz.demo.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName CasDemo
 * @Description TODO
 * @Author lzx
 * @Date 2020/4/2 10:53
 */
public class CASDemo {
    /**
     *  cas compareAndSet 比较并交换
     *  CAS的全称为Compare-And-Swap，它是一-条CPU并发原语。
     * 它的功能是判断内存某个位置的值是否为预期值，如果是则更改为新的值，这个过程是原子的。
     * CAS是一条CPU的原子指令，不会 造成所谓的数据不一致问题。
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean b = atomicInteger.compareAndSet(1, 6);
        //出现ABA问题
        System.out.println(atomicInteger.get());

        //解决ABA问题
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(10,1);
    }
}
