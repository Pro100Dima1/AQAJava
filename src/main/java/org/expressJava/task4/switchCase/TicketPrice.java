package org.expressJava.task4.switchCase;

import java.util.Scanner;

public class TicketPrice {
    public static void main(String[] args) {
        System.out.println(priceTicketInCurrentDaySwitch());
        System.out.println(priceTicketInCurrentDayIfElse());
    }

    public static int priceTicketInCurrentDayIfElse() {
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();

        int price = 0;
        if (day > 0 && day <= 5) {
            price = 300;
        } else if (day > 5 && day <= 7) {
            price = 450;
        } else {
            System.out.println("Притормози, дружок. В неделе всего 7 дней");
        }
        return price;
    }

    public static int priceTicketInCurrentDaySwitch() {
        Scanner scanner = new Scanner(System.in);
        int day = scanner.nextInt();

        int price = 0;
        switch (day) {
            case 1:
                price = 300;
                break;
            case 2:
                price = 300;
                break;
            case 3:
                price = 300;
                break;
            case 4:
                price = 300;
                break;
            case 5:
                price = 300;
                break;
            case 6:
                price = 450;
                break;
            case 7:
                price = 450;
                break;
            default:
                System.out.println("Дружок, уложись в дни недели, пожалуйста");
        }
        return price;
    }
}
