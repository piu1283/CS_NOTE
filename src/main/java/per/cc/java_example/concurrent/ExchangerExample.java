package per.cc.java_example.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 两个线程在预设点交换变量，先到达的等待对方。
 */
public class ExchangerExample {
    public static void main(String[] args) throws Exception {
        new Thread(new Staff("大胖", new Tool("笤帚", "扫地"), ex)).start();
        new Thread(new Staff("小白", new Tool("抹布", "擦桌"), ex)).start();
        synchronized (ExchangerExample.class) {
            ExchangerExample.class.wait();
        }
    }

    static Exchanger<Tool> ex = new Exchanger<>();

    static class Staff implements Runnable {

        String name;
        Tool tool;
        Exchanger<Tool> ex;

        Staff(String name, Tool tool, Exchanger<Tool> ex) {
            this.name = name;
            this.tool = tool;
            this.ex = ex;
        }

        @Override
        public void run() {
            System.out.printf("%s拿的工具是[%s]，他开始[%s]。。。", name, tool.name, tool.work);
            doingLongTime();
            System.out.printf("%s开始交换工具。。。", name);
            try {
                tool = ex.exchange(tool);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.printf("%s的工具变为[%s]，他开始[%s]。。。", name, tool.name, tool.work);
        }

        private void doingLongTime() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class Tool {

        String name;
        String work;

        Tool(String name, String work) {
            this.name = name;
            this.work = work;
        }

    }
}
