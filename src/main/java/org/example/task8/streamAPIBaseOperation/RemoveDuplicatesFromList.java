package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromList {
    public String name;
    public int age;

    public RemoveDuplicatesFromList(String name, int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "RemoveDuplicatesFromList{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveDuplicatesFromList that = (RemoveDuplicatesFromList) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromList persOne = new RemoveDuplicatesFromList("Petr", 12);
        RemoveDuplicatesFromList persTwo = new RemoveDuplicatesFromList("Olya", 15);
        RemoveDuplicatesFromList persThree = new RemoveDuplicatesFromList("Goga", 10);
        RemoveDuplicatesFromList persFour = new RemoveDuplicatesFromList("Goga", 10);

        List<RemoveDuplicatesFromList> listPerson = List.of(persOne, persTwo, persThree, persFour);
        List<RemoveDuplicatesFromList> listWithoutDuplicates = listPerson.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);
    }
}

