package ex2;

import java.util.concurrent.TimeUnit;

public class MFU {
    static Object scan_desk = new Object();
    static Object print_dev = new Object();
public void print(){
    synchronized (print_dev){
        System.out.println("Printing");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public void copy(){
      //  synchronized (print_dev,scan_desk){
    synchronized (scan_desk) {
        synchronized (print_dev) {
            System.out.println("Coping");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }
    public void scan(){
        synchronized (scan_desk){
            System.out.println("Scanning");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       MFU mfu = new MFU();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.scan();
            }
        }).start();
       new Thread(new Runnable() {
           @Override
           public void run() {
             mfu.copy();
           }
       }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mfu.print();
            }
        }).start();

    }
}
