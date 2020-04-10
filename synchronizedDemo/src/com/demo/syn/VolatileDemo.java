package com.demo.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Lining
 */
public class VolatileDemo {
    private Lock lock =  new ReentrantLock();
    private volatile int number = 0;
    public int getNumber(){
        return this.number;
    }
    public synchronized void intcrease(){
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lock.lock();
        try{
            this.number++;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        for(int i= 0; i<500; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileDemo.intcrease();
                }
            }).start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println("number"+volatileDemo.getNumber());
    }
}
