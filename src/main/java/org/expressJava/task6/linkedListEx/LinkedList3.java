package org.expressJava.task6.linkedListEx;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedList3 {
    public static void main(String[] args) {
        LinkedList<String> ex3 = new LinkedList<>(Arrays.asList("a", "b", "c"));

        System.out.println(ex3.peek());
        System.out.println(ex3.peekLast());
    }
}
