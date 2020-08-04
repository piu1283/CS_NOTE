package per.cc.java_example.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * using Semaphore
 */
public class MultiThreadPrinter3 {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphoresA = new Semaphore(1);
        Semaphore semaphoresB = new Semaphore(0);
        Semaphore semaphoresC = new Semaphore(0);
        Thread tA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoresA.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                semaphoresB.release();

            }
        });
        Thread tB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoresB.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                semaphoresC.release();

            }
        });
        Thread tC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphoresC.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                semaphoresA.release();

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
