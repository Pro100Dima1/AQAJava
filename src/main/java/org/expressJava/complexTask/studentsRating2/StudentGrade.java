package org.expressJava.complexTask.studentsRating2;

public class StudentGrade<T extends Number> {
    private String name;
    private String object;
    private T rank;

    public StudentGrade(String name, String object, T rank) {
        this.name = name;
        this.object = object;
        this.rank = rank;
    }

    public String getName3() {
        return name;
    }

    public String getObject() {
        return object;
    }

    public T getRank() {
        return rank;
    }
}
