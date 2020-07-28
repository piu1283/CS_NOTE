package per.cc.java_example.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadWriteLock {
    private AtomicInteger read;
    private AtomicInteger write;

    public ReadWriteLock() {
        this.read = new AtomicInteger();
        this.write = new AtomicInteger();
    }

    public void tryReadLock() throws InterruptedException {
        while (write.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
        read.getAndIncrement();
    }

    public void releaseReadLock() {
        read.getAndIncrement();
        notifyAll();
    }

    public void writeLock() throws InterruptedException {
        while (write.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
        write.getAndIncrement();
        while (read.get() > 0) {
            synchronized (this) {
                wait();
            }
        }
    }

    public void releaseWriteLock() {
        write.decrementAndGet();
        notifyAll();
    }

}
