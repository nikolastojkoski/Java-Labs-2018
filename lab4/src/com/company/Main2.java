package com.company;

import java.util.Scanner;

public class Main2 {

    private static volatile int currentBoxes = 0;

    static class Worker implements Runnable{
        @Override
        public synchronized void run() {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentBoxes++;
            System.out.println("Thread ID: " + Thread.currentThread().getName() + ", currentBoxes = " + currentBoxes);

            notify();

        }
    }

    static class Coordinator implements Runnable{

        private int numWorkers, numBoxes;

        Coordinator(int numWorkers, int numBoxes){
            this.numWorkers = numWorkers;
            this.numBoxes = numBoxes;
        }

        @Override
        public synchronized void run() {

            notify();

            while(currentBoxes < numBoxes){

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("finished one round of boxes");

                notifyAll();
            }

            System.out.println("finished all boxes");
        }
    }

    public static void main(String[] args) {

        int numWorkers = 0, boxes = 0;

        Scanner inp = new Scanner(System.in);
        try{
            System.out.println("Enter amount of workers: ");
            numWorkers = inp.nextInt();
            System.out.println("Enter amount of boxes: ");
            boxes = inp.nextInt();
        }catch(Exception e){
            System.out.println("Invalid input");
            return;
        }

        for(int i=0; i < numWorkers; i++){
            new Thread(new Worker()).start();
        }

        Thread coordinator = new Thread(new Coordinator(numWorkers, boxes));
        coordinator.start();

    }
}
