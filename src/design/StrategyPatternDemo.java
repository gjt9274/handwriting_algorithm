package design;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5: " + context.executeStrategy(10,5) );

        context = new Context(new OperationMultiply());
        System.out.println("10 + 5: " + context.executeStrategy(10,5) );

        context = new Context(new OperationSubstract());
        System.out.println("10 + 5: " + context.executeStrategy(10,5) );
    }
}

// 1. 策略接口
interface Strategy{
    int doOperation(int num1, int num2);
}

// 2. 实现不同的策略
class OperationAdd implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationMultiply implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class OperationSubstract implements Strategy{

    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

//3. 创建 context 类， 随着策略行为改变而改变
class Context {
    private final Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
