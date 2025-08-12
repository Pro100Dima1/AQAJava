package org.example.task8.streamAPIBaseOperation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromList {
    public String name;
    public int age;

    public RemoveDuplicatesFromList(String name, int age){
        this.name = name;
        this.age = age;
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
       RemoveDuplicatesFromList person1 = new RemoveDuplicatesFromList("Olya", 15);
       RemoveDuplicatesFromList person2 = new RemoveDuplicatesFromList("Nikita", 16);
       RemoveDuplicatesFromList person3 = new RemoveDuplicatesFromList("Igor", 10);
       RemoveDuplicatesFromList person4 = new RemoveDuplicatesFromList("Olya", 15);

       List<RemoveDuplicatesFromList> listOfperson = List.of(person1, person2, person3, person4);
       List<RemoveDuplicatesFromList> listWithoutDuplicates = listOfperson.stream()
               .distinct()
               .collect(Collectors.toList());
       System.out.println(listWithoutDuplicates);
    }
}

