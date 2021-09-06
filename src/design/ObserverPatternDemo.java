package design;


import java.util.ArrayList;
import java.util.List;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        BinaryObserver binaryObserver = new BinaryObserver(subject);
        HexObserver hexObserver = new HexObserver(subject);
        OctalObserver octalObserver = new OctalObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second sate change: 10");
        subject.setState(10);
    }
}

// 1. 创建 subject 类, 可以有多个对象(观察者)关注它
class Subject {
    List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState(){
        return state;
    }
    public void setState(int state){
        this.state = state;
        notifyAllObserver();
    }
    //添加观察者
    public void attach(Observer observer){
        observers.add(observer);
    }
    //通知所有观察者
    public void notifyAllObserver(){
        for(Observer observer: observers){
            observer.update();
        }
    }
}

// 1. 创建通用观察者类
abstract class  Observer{
    protected Subject subject;
    public abstract void update();
}

// 2. 创建具体的观察者
class BinaryObserver extends Observer{

    BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}

class HexObserver extends Observer{

    HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String:" + Integer.toHexString(subject.getState()));
    }
}

class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}
