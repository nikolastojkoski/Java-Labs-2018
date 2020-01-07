package com.company;

import java.util.Random;

public class Main {

    static final Object mutex = new Object();
    static volatile Integer storage = 0;

    static class Worker implements Runnable{
        @Override
        public void run() {
            Random rand = new Random();

            for(int i=0; i<1000; i++){
                synchronized (mutex){
                    storage = rand.nextInt(100);
                    System.out.println("Worker name: " + Thread.currentThread().getName() + ", Generated value = " + storage);

                    mutex.notify();

                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Worker.run finished");

            synchronized (mutex){
                mutex.notify();
            }
        }
    }

    static class Consumer implements Runnable{
        @Override
        public void run() {

            synchronized (mutex){
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0; i<1000; i++) {
                synchronized (mutex) {

                    System.out.println("Consumer name: " + Thread.currentThread().getName() + ", received value: " + storage);

                    mutex.notify();

                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Consumer.run finished");
        }
    }


    public static void main(String[] args) throws InterruptedException {

        new Thread(new Consumer()).start();
        new Thread(new Worker()).start();

    }

}
