package design.singleton;

public class LazySingletonDemo {
}

class LazySingleton{
    private  static LazySingleton instance;
    private LazySingleton(){};

    //线程不安全
//    public static LazySingleton getInstance(){
//        if (instance == null) {
//            instance = new LazySingleton();
//        }
//
//        return instance;
//    }
    //线程安全
    public  synchronized static  LazySingleton getInstance(){
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

}