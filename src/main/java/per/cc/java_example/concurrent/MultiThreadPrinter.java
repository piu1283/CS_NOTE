package per.cc.java_example.concurrent;

public class MultiThreadPrinter {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Printer1("A"));
        Thread t2 = new Thread(new Printer1("B"));
        Thread t3 = new Thread(new Printer1("C"));
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}

/**
 * did not using any lock
 */
class Printer1 implements Runnable{
    private static int count = 0;

    private String name;

    public Printer1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (count < 30){
            switch (count % 3){
                case 0:
                    if ("A".equals(name)) {
                        this.printAndIncr();
                    }
                    break;
                case 1:
                    if ("B".equals(name)) {
                        this.printAndIncr();
                    }
                    break;
                case 2:
                    if ("C".equals(name)) {
                        this.printAndIncr();
                    }
                    break;
            }
        }
    }

    private void printAndIncr() {
        System.out.print(name);
        count++;
    }
}