package com.demo.syn;

/**
 * @author Lining
 */
public class SyncharonizedDemo {
    private boolean ready = false;

    private int result = 0;

    private int number = 1;

    //写操作
    public void write() {
        ready = true;
        number = 2;
    }

    public void read() {
        if (ready) {
            result = number * 3;
        }
        System.out.println("result的值为:" + result);//内部注程类
    }

    public class ReadWriteThread extends Thread {
        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                write();
            }else{
               read();
            }
        }
    }

    public static void main (String[] args ){
        SyncharonizedDemo synDemo = new SyncharonizedDemo();
        synDemo.new ReadWriteThread(true).start();
        synDemo.new ReadWriteThread(false).start();
    }
}