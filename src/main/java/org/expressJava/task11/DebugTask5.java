package org.expressJava.task11;

public class DebugTask5 {
    public static void main(String[] args) {
        Person person = new Person("Alice", 25);
        updateAge(person);
        System.out.println("Updated age: " + person.getAge());
        System.out.println(person.hashCode());
    }

    public static void updateAge(Person person) {
        person = new Person(person.getName(), person.getAge() + 1);
        System.out.println(person.hashCode());
    }
}

class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}