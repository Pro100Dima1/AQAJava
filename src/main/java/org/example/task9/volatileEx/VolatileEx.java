package org.example.task9.volatileEx;

public class VolatileEx {
    static volatile boolean isStoping = false;
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!isStoping) {
                counter++;

            }
        });

        t1.start();
        //System.out.println(counter);
        Thread.sleep(2000);
        isStoping = true;
        t1.join();
    }
}
