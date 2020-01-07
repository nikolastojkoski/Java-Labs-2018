package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Object mutex = new Object();
    private static volatile int currentBoxes = 0;

    static class Worker implements Runnable{
        @Override
        public void run() {

            while(!Thread.interrupted()){

                synchronized (Thread.currentThread()){
                    try {
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                synchronized (mutex) {

                    currentBoxes++;
                    System.out.println("Thread ID: " + Thread.currentThread().getId() +
                            " Collected box of apples N " + currentBoxes);
                }

                synchronized (Thread.currentThread()){
                    Thread.currentThread().notify();
                }

            }
        }
    }

    static class Coordinator implements Runnable{

        private int numWorkers, numBoxes;
        private ArrayList<Thread> workers;

        Coordinator(int numWorkers, int numBoxes, ArrayList<Thread> workers){
            this.numWorkers = numWorkers;
            this.numBoxes = numBoxes;
            this.workers = workers;
        }

        @Override
        public void run() {

            while(currentBoxes < numBoxes){

                int remainingBoxes = numBoxes - currentBoxes;

                while(remainingBoxes < workers.size()){
                    workers.get(workers.size() - 1).interrupt();
                    workers.remove(workers.size() - 1);
                }

                for(Thread thr: workers){
                    synchronized (thr){
                        thr.notify();
                        try {
                            thr.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("Collected a round of apple boxes");

            }
            for(Thread thr: workers){
                thr.interrupt();
            }
            System.out.println("Coordinator job finished, required apple boxes collected");
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

        if(numWorkers < 1 || boxes < 1){
            System.out.println("Invalid input");
            return;
        }

        ArrayList<Thread> workers = new ArrayList<Thread>();
        workers.ensureCapacity(numWorkers);

        for(int i=0; i < numWorkers; i++){
            workers.add(new Thread(new Worker()));
        }

        workers.forEach(Thread::start);

        Thread coordinator = new Thread(new Coordinator(numWorkers, boxes, workers));
        coordinator.start();

    }
}
