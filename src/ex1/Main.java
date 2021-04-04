package ex1;

import java.io.File;

public class Main {
static Object monitor = new Object();
static volatile char liter = 'A';

public static void main(String[] args){

        new Thread(new Runnable(){
@Override
public void run(){
synchronized (monitor){
        for(int i=0;i< 5;i++){
        while(liter!='A'){
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.print("A");
        liter='B';
        monitor.notifyAll();
        }
     }
}
        }).start();
    new Thread(new Runnable(){
        @Override
        public void run(){
            synchronized (monitor){
                for(int i=0;i< 5;i++){
                    while(liter!='B'){
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    liter='C';
                    monitor.notifyAll();
                }
            }
        }
    }).start();
    new Thread(new Runnable(){
        @Override
        public void run(){
            synchronized (monitor){
                for(int i=0;i< 5;i++){
                    while(liter!='C'){
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    System.out.println();
                    liter='A';
                    monitor.notifyAll();
                }
            }
        }
    }).start();

        }


}
