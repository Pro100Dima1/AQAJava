package org.expressJava.task8.functionalInterface;

public class RunnableAnonymusClass {
    public static void main(String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous class!");
            }
        };
        run.run();
    }
}
