package org.expressJava.complexTask.studentsRating2;

import java.util.ArrayList;
import java.util.List;

public class GradeService<T extends Number> {
    private List<StudentGrade> grades = new ArrayList<>();

    public synchronized void addGrade(StudentGrade<T> grade) throws InvalidGradeException {
        if (grade.getRank().intValue() < 0) {
            throw new InvalidGradeException("Rank is empty");
        }
        grades.add(grade);
    }

    public synchronized int avgRank(String object) {
        return (int) grades.stream()
                .filter(g -> g.getObject().equals(object))
                .mapToInt(g -> g.getRank().intValue())
                .average()
                .orElseThrow(() -> new InvalidGradeException("No grade found"));
    }
}
