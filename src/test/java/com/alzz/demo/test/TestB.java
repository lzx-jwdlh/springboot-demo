package com.alzz.demo.test;

public class TestB {
    public static void main(String[] args) {
        System.out.println(add2(1,2));
    }
    static int add2(int a,int b){
        while (a>0) {
            b++;
            a--;
        }
        while (a<0) {
            b--;
            a++;
        }
        return b;
    }
}
