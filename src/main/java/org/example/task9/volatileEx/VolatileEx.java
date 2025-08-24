package org.example.task9.volatileEx;

public class VolatileEx extends Thread {
    static volatile boolean isStopping = false;
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!isStopping) {
                counter++;
                //System.out.println(counter);
            }
        });

        t1.start();
        System.out.println(counter);
        t1.sleep(2000);
        isStopping = true;
        t1.join();

    }
}
