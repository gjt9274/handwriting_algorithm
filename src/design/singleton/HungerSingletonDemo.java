package design.singleton;

public class HungerSingletonDemo {

    public static void main(String[] args) {
        HungerSingleton singleton = HungerSingleton.getInstance();
        singleton.showMessage();
    }
}

class HungerSingleton{
    //在类加载时就已经实例化
    private static final HungerSingleton instance = new HungerSingleton();

    private HungerSingleton(){}; //构造方法私有化，就不会被实例化

    //获取唯一可用的对象
    public static HungerSingleton getInstance() {
        return instance;
    }
    public void showMessage(){
        System.out.println("hello world!");
    }

}