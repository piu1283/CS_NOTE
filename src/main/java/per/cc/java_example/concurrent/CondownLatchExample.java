package per.cc.java_example.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * 某个线程到达预设点时就在此等待，等所有的线程都到达时，大家再一起向下个预设点出发。如此循环反复下去。
 */
public class CondownLatchExample {
    static final int COUNT = 20;

    public static void main(String[] args) throws Exception {
        new Thread(new Teacher(cdl)).start();
        sleep(1);
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Student(i, cdl)).start();
        }
        synchronized (CondownLatchExample.class) {
            CondownLatchExample.class.wait();
        }
    }

    static CountDownLatch cdl = new CountDownLatch(COUNT);

    static class Teacher implements Runnable {

        CountDownLatch cdl;

        Teacher(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.println("老师发卷子。。。");
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("老师收卷子。。。");
        }

    }

    static class Student implements Runnable {

        CountDownLatch cdl;
        int num;

        Student(int num, CountDownLatch cdl) {
            this.num = num;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.printf("学生(%d)写卷子。。。", num);
            try {
                sleep(new Random().nextInt(4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("学生(%d)交卷子。。。", num);
            cdl.countDown();
        }

    }
}
