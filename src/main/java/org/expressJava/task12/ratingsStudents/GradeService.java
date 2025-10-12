package org.expressJava.task12.ratingsStudents;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> {
    private final List<StudentGrade<T>> gradeArray = new ArrayList<>();

    public synchronized void addGrade(StudentGrade<T> grade) throws InvalidGradeException {
        if (grade.getGrade().intValue() < 0) {
            throw new InvalidGradeException("The rating cannot be negative");
        }
        gradeArray.add(grade);
    }

    public synchronized int averageGrade(String object) {
        return (int) gradeArray.stream()
                .filter(s -> s.getSubject().equalsIgnoreCase(object))
                .mapToInt(s -> s.getGrade().intValue())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("An invalid argument was entered in the calculation"));
    }

    public int returnListGrade(){
        return gradeArray.size();
    }
}
