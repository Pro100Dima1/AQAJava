package org.example.task6.priorityQueue;

import java.util.PriorityQueue;

public class PriorityQueue1 {
    public static void main(String[] args) {
        PriorityQueue<Integer> ex1 = new PriorityQueue<>();
        ex1.offer(1);
        ex1.offer(8);
        ex1.offer(2);
        ex1.offer(6);
        ex1.offer(4);
        while (!ex1.isEmpty()){
            System.out.println(ex1.poll());
        }
    }
}
