package per.cc.java_example.singleton;

interface SomeMethod {
    public int add(int a, int b);
}

public enum SingletonEnum2 implements SomeMethod {
    INSTANCE;

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}




