package org.example.task9.oneThread;

public class OneThread implements Runnable{

    @Override
    public void run() {
        System.out.println("Привет мир");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
