package org.example.task9.synchronizedEx;

public class Counter extends Thread {
    private static int counter = 0;

    public synchronized static void incremntCounter() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incremntCounter();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                incremntCounter();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
