package sample;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab4{

    private static final Object mutex = new Object();
    private volatile int currentBoxes = 0;
    private volatile TextArea output;

    private class Worker implements Runnable{
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
                    output.appendText("Thread ID: " + Thread.currentThread().getId() +
                            " Collected box of apples N " + currentBoxes + "\n");
                }

                synchronized (Thread.currentThread()){
                    Thread.currentThread().notify();
                }

            }
        }
    }

    private class Coordinator implements Runnable{

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
                output.appendText("Collected a round of apple boxes \n");

            }
            for(Thread thr: workers){
                thr.interrupt();
            }
            output.appendText("Coordinator job finished, required apple boxes collected \n");
        }
    }

    public void start(String numWorkers_, String numBoxes_, TextArea output_) {

        output = output_;
        int numWorkers = 0, boxes = 0;
        try{
            numWorkers = Integer.parseInt(numWorkers_);
            boxes = Integer.parseInt(numBoxes_);
        }catch(NumberFormatException e){
            e.printStackTrace();
            output.appendText("Invalid input \n");
            return;
        }

        if(numWorkers < 1 || boxes < 1){
            output.appendText("Invalid input \n");
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

        try {
            coordinator.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
