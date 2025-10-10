package org.expressJava.task12.ratingsStudents;

public class StudentGrade<T extends Number> {
    private final String nameStudent;
    private final String subject;
    private final T grade;

    public StudentGrade(String nameStudent, String subject, T grade) {
        this.nameStudent = nameStudent;
        this.subject = subject;
        this.grade = grade;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getSubject() {
        return subject;
    }

    public T getGrade() {
        return grade;
    }
}
