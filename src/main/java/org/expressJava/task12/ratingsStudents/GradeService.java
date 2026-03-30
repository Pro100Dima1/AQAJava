package org.expressJava.task12.ratingsStudents;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> {
    List<StudentGrade> grades = new ArrayList<>();

    public synchronized void addGrade(StudentGrade<T> grade) throws InvalidGradeException {
        if (grade.getRank().intValue() < 0) {
            throw new InvalidGradeException("Rank is not valid");
        }
        grades.add(grade);
    }

    public synchronized int avgRank(String object) throws InvalidGradeException {
       return (int)grades.stream()
                .filter(g -> g.getObject().equals(object))
                .mapToInt(g -> g.getRank().intValue())
                .average()
                .orElseThrow(() -> new InvalidGradeException("No rank found"));
    }
}
