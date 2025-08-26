package org.example.task9.oneThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OneThread oneThread = new OneThread();

        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(oneThread);
            t1.start();
            t1.join();
        }
    }
}
