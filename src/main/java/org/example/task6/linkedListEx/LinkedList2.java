package org.example.task6.linkedListEx;

import java.util.LinkedList;
import java.util.List;

public class LinkedList2 {
    public static void main(String[] args) {
        LinkedList<String> que = new LinkedList<>();
        que.add("Babushka");
        que.add("Dedushka");
        que.add("Vnuchka");
        while (!que.isEmpty()){
            System.out.println("Обработка задач в очереди : " + que.poll());
        }
    }
}
