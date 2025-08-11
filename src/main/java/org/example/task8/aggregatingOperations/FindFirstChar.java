package org.example.task8.aggregatingOperations;
import java.util.List;

public class FindFirstChar {
    public static void main(String[] args) {
        List<String> names = List.of("Катя", "Мирон", "Борис", "Агапий");
        String name = names.stream()
                .filter(n -> n.startsWith("Б"))
                .findFirst()
                .get();
        System.out.println(name);
    }
}
