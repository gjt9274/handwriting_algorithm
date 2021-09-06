package design.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy {
    public static void main(String[] args) {
        // 3. 获取代理类
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(SendSMS.class.getClassLoader());
        // 设置被代理类
        enhancer.setSuperclass(SendSMS.class);
        // 设置方法拦截器
        enhancer.setCallback(new ProxyMethodInterceptor());
        //创建代理类
        SendSMS proxy = (SendSMS)enhancer.create();
        proxy.send("buaa");

    }
}

// 1. 真实对象，不用实现接口
class SendSMS{
    public String send(String message){
        System.out.println("send message: " + message);
        return message;
    }
}


//2. 自定义方法拦截器
class ProxyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 调用方法前
        System.out.println("before " + method.getName());

        Object res = methodProxy.invokeSuper(o,args);

        //调用方法后
        System.out.println("after " + method.getName());
        return res;
    }
}