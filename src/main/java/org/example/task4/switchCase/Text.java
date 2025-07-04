package org.example.task4.switchCase;

import java.util.Scanner;

public class Text {
    public static void main(String[] args) {
        System.out.println(text());

    }

    public static String text() {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        String textResult = "";
        switch (text) {
            case "start":
                textResult = "Система запущена";
                break;
            case "stop":
                textResult = "Система остановлена";
                break;
            case "restrt":
                textResult = "Система перезапущена";
                break;
            case "status":
                textResult = "Статус системы";
                break;
            default:
                textResult = "Введена внесистемная команда";
        }
        return textResult;
    }
}
