package com.example.demo.main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuguangquan
 * @date 2020/5/21 周四
 */
public class VolatileSample {

    private static volatile AtomicInteger a = new AtomicInteger(0);
    private static AtomicInteger b = new AtomicInteger(0);
    private static int c = 0;
    private static Lock re = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int k = 0; k++ < 100; ) {
            CountDownLatch cdl = new CountDownLatch(10);
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    int j = 0;
                    while (j++ < 100)
                        print();
                    cdl.countDown();
                }).start();
            }
            cdl.await();
            System.out.println(c + "，" + a.get());
        }
    }


    static void print() {
        re.lock();
        c++;
        a.incrementAndGet();
        re.unlock();
    }


}
