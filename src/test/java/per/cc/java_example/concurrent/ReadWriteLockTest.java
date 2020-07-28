package per.cc.java_example.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ReadWriteLockTest {
    @Test
    public void testRWLock() throws InterruptedException {
        ReadWriteLock rwlock = new ReadWriteLock();
        List<Thread> tlist = new ArrayList<>();
        Random r = new Random();
        int wCount = 0;
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new ReadThread(i, rwlock));
            if (r.nextInt(10) % 3 == 0) {
                Thread t2 = new Thread(new WriteThread(wCount++, rwlock));
                tlist.add(t2);
                t2.start();
            }
            tlist.add(t);
            t.start();
        }
        for (Thread t : tlist) {
            t.join();
        }
    }
}

class ReadThread extends Thread{
    public int i;
    public ReadWriteLock readWriteLock;

    public ReadThread(int i, ReadWriteLock readWriteLock) {
        this.i = i;
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run() {
        try {
            readWriteLock.tryReadLock();
            Thread.sleep(1000);//模拟耗时
            System.out.println("第"+i+"个读任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.releaseReadLock();
        }
    }
}


class WriteThread implements Runnable{
    public int i;
    public ReadWriteLock readWriteLock;

    public WriteThread(int i, ReadWriteLock readWriteLock) {
        this.i = i;
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run() {
        try {
            readWriteLock.writeLock();
            Thread.sleep(1000);
            System.out.println("第"+i+"个写任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.releaseWriteLock();
        }
    }
}