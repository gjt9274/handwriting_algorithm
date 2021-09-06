package design.factory;

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory miFactory =  new XiaoMiFactory();
        AbstractFactory appleFactory =  new AppleFactory();
        miFactory.makePC();
        miFactory.makePhone();

        appleFactory.makePC();
        appleFactory.makePhone();

    }
}

// 1. 产品1接口
interface Phone{
    void make();
}

// 2. 产品2接口
interface PC{
    void make();
}


// 3. 产品1的具体产品
class MiPhone implements Phone {
    public MiPhone() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make xiaomi phone!");
    }
}

class IPhone implements Phone {
    public IPhone() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make iphone!");
    }
}

// 4. 产品 2 的具体产品
class MiPC implements PC {
    public MiPC() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make xiaomi PC!");
    }
}

class MAC implements PC {
    public MAC() {
        this.make();
    }
    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make MAC!");
    }
}

// 5. 定义抽象工厂
interface AbstractFactory{
    Phone makePhone();
    PC makePC();
}

// 6 具体工厂，可以生产一系列产品，而不是具体类型
class XiaoMiFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
    @Override
    public PC makePC() {
        return new MiPC();
    }
}

class AppleFactory implements AbstractFactory {
    @Override
    public Phone makePhone() {
        return new IPhone();
    }
    @Override
    public PC makePC() {
        return new MAC();
    }
}