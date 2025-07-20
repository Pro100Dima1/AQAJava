package org.example.task6.linkedListEx;

import java.util.LinkedList;

public class LinkedList2 {
    public static void main(String[] args) {
        LinkedList<String> que = new LinkedList<>();
        que.add("Один");
        que.add("Два");
        que.add("Три");

        while(!que.isEmpty()){
            System.out.println("Обработка задачи номер " + que.poll());
        }
    }
}
