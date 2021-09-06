package com.gongjintao.pc;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiyBlockingQuque {
    private Object[] container;

    private int putIndex;
    private int takeIndex;
    private int count;
    private ReentrantLock lock;

    private final Condition notEmpty;//消费者
    private final Condition notFull; //生产者

    public DiyBlockingQuque(int size, boolean fair){
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        container = new Object[size];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }
    public DiyBlockingQuque(int size) {this(size, false);}

    public void put(Object o){
        if (o == null) {
            throw new NullPointerException();
        }
        lock.lock();
        try{
            while (count == container.length) {
                //阻塞生产者
                notFull.await();
            }
            container[putIndex] = o;
            if (++putIndex == container.length) putIndex = 0;
            count++;
            //通知消费者
            notEmpty.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public Object take(){
        lock.lock();
        try{
            while (count == 0) {
                //阻塞消费者
                notEmpty.await();
            }
            Object o = container[takeIndex];
            if(++takeIndex == container.length) takeIndex = 0;
            count--;
            //通知生产者
            notFull.signal();
            return o;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        DiyBlockingQuque diyBlockingQuque = new DiyBlockingQuque(10);

        new Thread(()->{
            for (int i = 0; i < 20; i++){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("生产者生产了第" + i + "个产品");
                diyBlockingQuque.put(i);
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            for(int i = 0; i < 20; i++){
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Object o = diyBlockingQuque.take();
                System.out.println("消费者消费了第" + o.toString() + "个产品");
            }
        }).start();


    }
}
