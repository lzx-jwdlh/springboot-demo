package com.alzz.demo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DateTest {
    static int N = 1000010;
    static boolean[] f = new boolean[N];
    static int cid ;
    static int did ;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        while(n -- > 0)
        {
            String[] s1 = reader.readLine().split(" ");
            for(int i = 0;i < s1.length;i++)
            {
                int t = Integer.parseInt(s1[i]);
                maxv = Math.max(maxv, t);
                minv = Math.min(minv, t);
                if(f[t]) cid = t;
                f[t] = true;
            }
        }
        for(int i = minv ;i <= maxv ;i ++)
        {
            if(!f[i])
            {
                did = i;
                break;
            }
        }
        System.out.println(did + " " + cid);
    }
}
