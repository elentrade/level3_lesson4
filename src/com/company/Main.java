package com.company;

import java.io.File;

public class Main {
public static class Litter{
    public synchronized void printLitter(char l) {
        for (int i = 0; i < 5; i++) {
            System.out.println(l);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

    public static void main(String[] args) {
    Litter litter = new Litter();
        Thread tr1 = new Thread(new Runnable() {
            @Override
            public void run() {
                litter.printLitter('A');
            }
        });
        Thread tr2 = new Thread(new Runnable() {
            @Override
            public void run() {
                litter.printLitter('B');
            }
        });
        Thread tr3 = new Thread(new Runnable() {
            @Override
            public void run() {
                litter.printLitter('C');
            }
        });
        tr1.start();
        tr2.start();
        tr3.start();


    }
}
