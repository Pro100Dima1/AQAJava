package org.example.task6.LinkedListEx;

import java.util.LinkedList;

public class LinkedList2 {
    public static void main(String[] args) {
        LinkedList<String> que = new LinkedList<>();
        que.add("Первый");
        que.add("Второй");
        que.add("Третий");

        for (String i : que){
            System.out.println(que.poll());
        }
    }
}
