package com.example.demo.main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程循环输出ABCABCABCABCABCABC....
 * <p>
 * 其实就是保证打印输出的时候只有一个线程且为当前线程
 *
 * @author xuguangquan
 * @date 2020/5/16 周六
 */
public class CirclePrintMain {
    // 可以感受下要volatile和不要volatile修饰打印的结果,结合jmm就知道不加的造成的错误现象原因了
    // 也可以使用原子类来保证++操作
//    private static volatile int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);
    // lock来保证只有一个线程打印
    private static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            CountDownLatch cdl = new CountDownLatch(6);
            new CirclePrint().print(cdl);
        }
    }


    static class CirclePrint {
        //        private volatile int count = 0;
        private AtomicInteger count = new AtomicInteger(0);

        public void print(CountDownLatch cdl) throws InterruptedException {
            new Thread(() -> {
                print(cdl, "A", 0);
            }).start();
            new Thread(() -> {
                print(cdl, "B", 1);
            }).start();
            new Thread(() -> {
                print(cdl, "C", 2);
            }).start();
            new Thread(() -> {
                print(cdl, "D", 3);
            }).start();
            new Thread(() -> {
                print(cdl, "E", 4);
            }).start();
            new Thread(() -> {
                print(cdl, "F", 5);
            }).start();
            cdl.await();
            System.out.println();
        }

//        private void print(CountDownLatch cdl, String content, int threadNo) {
//            while (count < 10) {
//                if (count % 6 == threadNo && count < 10) {
////                lock.lock();
////                try {
//                    System.out.print(content);
//                    count++;
////                } finally {
////                    lock.unlock();
////                }
//                }
//            }
//            cdl.countDown();
//        }

        private void print(CountDownLatch cdl, String content, int threadNo) {
            while (count.get() < 10) {
                if (count.get() % 6 == threadNo && count.get() < 10) {
//                lock.lock();
//                try {
                    System.out.print(content);
                    count.getAndIncrement();
//                } finally {
//                    lock.unlock();
//                }
                }
            }
            cdl.countDown();
        }
    }


}
