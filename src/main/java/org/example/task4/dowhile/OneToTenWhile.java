package org.example.task4.dowhile;

public class OneToTenWhile {
    public static void main(String[] args) {
        numbers();
    }

    public static void numbers() {

        int i = 1;
        do {
            System.out.println(i);
            i++;
        } while (i <= 10);
    }
}
