package per.cc.java_example.concurrent;

/**
 * Created by Chen on 7/11/20.
 */
public class ThreadExample {
    public static void main(String[] args) {
        t1 t1 = new t1();
        Thread t = new Thread(t1);
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.tellToStop();
    }
}


class t1 implements Runnable {
    volatile boolean stop = false;

    public void tellToStop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(100);
        }
    }
}