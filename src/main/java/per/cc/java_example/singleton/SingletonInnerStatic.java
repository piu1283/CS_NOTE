package per.cc.java_example.singleton;

public class SingletonInnerStatic {
    private SingletonInnerStatic() {

    }

    private static class Instance{
        private static final SingletonInnerStatic ins = new SingletonInnerStatic();
    }

    public static SingletonInnerStatic getInstance() {
        return Instance.ins;
    }
}
