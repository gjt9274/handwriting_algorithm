package design.proxy;
public class StaticProxy {
    public static void main(String[] args) {
        SmsService target = new SmsServiceImpl();
        SmsProxy proxy = new SmsProxy(target);
        proxy.send("buaa");
    }
}


// 1. 定义真实对象和代理对象共有的接口
interface SmsService{
    String send(String message);
}

// 2. 实现接口，即真实对象
class SmsServiceImpl implements SmsService{

    @Override
    public String send(String message) {
        System.out.println("send message: " + message);
        return message;
    }
}

// 3. 代理对象，也要实现接口，并增强方法
class SmsProxy implements SmsService{

    // 需要传入真实对象
    private final SmsService target;

    SmsProxy(SmsService target) {
        this.target = target;
    }

    @Override
    public String send(String message) {
        //调用方法前
        System.out.println("before send message");

        target.send(message);

        //调用方法后
        System.out.println("after send message");

        return message;
    }
}

