package com;

import javafx.scene.control.TextArea;
import java.util.Random;

public class Lab3 {

    private final Object mutex = new Object();
    private volatile Integer storage = 0;
    private volatile TextArea output;
    private int amount;

    private class Worker implements Runnable{
        @Override
        public void run() {
            Random rand = new Random();

            for(int i=0; i<amount; i++){
                synchronized (mutex){
                    storage = rand.nextInt(100);

                    
                    output.appendText("Worker name: " + Thread.currentThread().getName() + ", Generated value = " + storage + "\n");

                    mutex.notify();

                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            output.appendText("Worker.run finished \n");

            synchronized (mutex){
                mutex.notify();
            }
        }
    }

    private class Consumer implements Runnable{
        @Override
        public void run() {

            synchronized (mutex){
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(int i=0; i<amount; i++) {
                synchronized (mutex) {

                    output.appendText("Consumer name: " + Thread.currentThread().getName() + ", received value: " + storage + "\n");

                    mutex.notify();

                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            output.appendText("consumer.run finished \n");
        }
    }


    public void start(String amount_, TextArea output_){

        output = output_;

        try{
            amount = Integer.parseInt(amount_);
        }catch(NumberFormatException e){
            e.printStackTrace();
            output.appendText("Invalid amount \n");
            return;
        }

        if(amount <= 0) {
            output.appendText("Invalid amount \n");
            return;
        }

        Thread consumerThread = new Thread(new Consumer());
        consumerThread.start();

        Thread workerThread = new Thread(new Worker());
        workerThread.start();

        try {
            consumerThread.join();
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
