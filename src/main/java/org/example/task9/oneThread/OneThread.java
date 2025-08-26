package org.example.task9.oneThread;

public class OneThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Привет из потока!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
