package per.cc.java_example.concurrent;

import java.util.concurrent.CyclicBarrier;

import static jdk.nashorn.internal.objects.Global.println;

/**
 * 某个线程到达预设点时就在此等待，等所有的线程都到达时，大家再一起向下个预设点出发。如此循环反复下去。
 */
public class CycleBarrier {
    static final int COUNT = 5;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Staff(i, cb)).start();
        }
        synchronized (CycleBarrier.class) {
            CycleBarrier.class.wait();
        }
    }

    static CyclicBarrier cb = new CyclicBarrier(COUNT, new Singer());

    static class Singer implements Runnable {

        @Override
        public void run() {
            println("为大家唱歌。。。");
        }

    }

    static class Staff implements Runnable {

        CyclicBarrier cb;
        int num;

        Staff(int num, CyclicBarrier cb) {
            this.num = num;
            this.cb = cb;
        }

        @Override
        public void run() {
            System.out.printf("员工(%d)出发。。。", num);
            doingLongTime();
            println("员工(%d)到达地点一。。。", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("员工(%d)再出发。。。", num);
            doingLongTime();
            System.out.printf("员工(%d)到达地点二。。。", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("员工(%d)再出发。。。", num);
            doingLongTime();
            System.out.printf("员工(%d)到达地点三。。。", num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("员工(%d)结束。。。", num);
        }

        private void doingLongTime() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
