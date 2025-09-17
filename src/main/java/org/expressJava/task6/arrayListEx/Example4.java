package org.expressJava.task6.arrayListEx;

import java.util.ArrayList;

public class Example4 {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        int sum = 0;
        for(int i : arr){
            sum +=  i;
        }
        System.out.println("Сумма = " + sum);
    }
}
