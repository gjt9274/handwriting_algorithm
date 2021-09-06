package design.singleton;

public class DCLSingletonDemo {
    public static void main(String[] args) {
        DCLSingleton singleton = DCLSingleton.getInstance();
        singleton.showMessage();
    }
}

class DCLSingleton{
    private volatile static DCLSingleton instance;
    private DCLSingleton(){}
    public static DCLSingleton getInstance(){
        if (instance == null){
            synchronized (DCLSingleton.class) {
                if (instance == null){
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
    public void showMessage(){
        System.out.println("hello world!");
    }
}