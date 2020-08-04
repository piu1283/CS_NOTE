package per.cc.java_example.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * using lock and condition
 */
public class MultiThreadPrinter2 {
    static char curPrint = 'A';

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Thread tA = new Thread(() -> {
            int i = 10;
            while (i >= 0) {
                lock.lock();
                try {
                    while (curPrint != 'A') {
                        conditionA.await();
                    }
                    System.out.println(curPrint);
                    curPrint = 'B';
                    i--;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });


        Thread tB = new Thread(() -> {
            int i = 10;
            while (i >= 0) {
                lock.lock();
                try {
                    while (curPrint != 'B') {
                        conditionB.await();
                    }
                    System.out.println(curPrint);
                    curPrint = 'C';
                    i--;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread tC = new Thread(() -> {
            int i = 10;
            while (i >= 0) {
                lock.lock();
                try {
                    while (curPrint != 'C') {
                        conditionC.await();
                    }
                    System.out.println(curPrint);
                    curPrint = 'A';
                    i--;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        tA.start();
        tB.start();
        tC.start();
        tA.join();
        tB.join();
        tC.join();
    }
}
