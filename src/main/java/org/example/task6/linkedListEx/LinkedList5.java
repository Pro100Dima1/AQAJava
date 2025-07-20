package org.example.task6.linkedListEx;

import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedList5 {
    public static void main(String[] args) {
        LinkedList<String> ex5 = new LinkedList<>();
        ex5.add("a");
        ex5.add("b");
        ex5.add("c");

        ListIterator<String> listIterator = ex5.listIterator();

        while (listIterator.hasNext()) {
            String alphavit = listIterator.next();
            System.out.println(alphavit);
        }

        while (listIterator.hasPrevious()) {
            String prevAlphavit = listIterator.previous();
            System.out.println(prevAlphavit);
        }
    }
}
