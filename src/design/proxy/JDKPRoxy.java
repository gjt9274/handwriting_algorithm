package design.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKPRoxy {
    public static void main(String[] args) {
        SmsService target = new SmsServiceImpl();
        // 4. 调用Proxy得到代理实例
        SmsService proxy = (SmsService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new SMSProxyHandler(target)
        );
        proxy.send("buaa");
    }
}


// 1. 定义接口，真实对象需要实现接口
// 2. 定义接口的实现类

// 3. 定义 JDK 代理类
class SMSProxyHandler implements InvocationHandler{
    // 需要传入真实对象
    private final Object target;

    SMSProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法调用前
        System.out.println("before send message");

        Object res = method.invoke(target,args);

        //方法调用后
        System.out.println("after send message");
        return res;
    }
}
