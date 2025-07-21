package org.example.task6.linkedHashMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkedHashMap3 {
    static LinkedHashMap<Integer, String> userHistory = new LinkedHashMap<>();
    static private final int MAX_SIZE = 10;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String user = scanner.nextLine();
            int id = scanner.nextInt();
            System.out.println(addUser(user, id));
            if (userHistory.size() > MAX_SIZE) {
                upDateLastElement();
            }
            printMap();
        }
    }

    public static void upDateLastElement() {
        Map.Entry<Integer, String> lastEnrty = null;
        for (Map.Entry<Integer, String> entry : userHistory.entrySet()) {
            lastEnrty = entry;
        }
        if (lastEnrty != null) {
            userHistory.put(lastEnrty.getKey(), "Последнее значение обновлено");
        }
    }

    public static LinkedHashMap<Integer, String> addUser(String user, int id) {
        userHistory.put(id, user);
        return userHistory;
    }

    public static void printMap() {
        for (Map.Entry<Integer, String> entry : userHistory.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
