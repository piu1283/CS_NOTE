package per.cc.java_example.singleton;

public class test {
    public static void main(String[] args) {
        int add = SingletonEnum.INSTANCE.add(1, 1);
        System.out.println(add);
    }
}
