package complexTasks;

import org.expressJava.task12.ratingsStudents.GradeService;
import org.expressJava.task12.ratingsStudents.InvalidGradeException;
import org.expressJava.task12.ratingsStudents.StudentGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GradeServiceTest {

    @Test
    @DisplayName("Проверка добавления студента в список")
    public void checkAddStudentInList() throws InvalidGradeException {
        GradeService<Integer> student = new GradeService<>();
        student.addGrade(new StudentGrade<>("Olya", "Chemistry", 3));
        assertEquals(1, student.returnListGrade());
    }

    @Test
    @DisplayName("Проверка отрицательной оценки")
    public void checkAddNegativeGrade() {
        GradeService<Integer> student = new GradeService<>();
        assertThrows(InvalidGradeException.class, () ->
                student.addGrade(new StudentGrade<>("Misha", "Math", -5)));
    }

    @Test
    @DisplayName("Проверка рассчета срадней оценки по предмету")
    public void checkAvgGrade() throws InvalidGradeException {
        GradeService<Integer> student = new GradeService<>();
        student.addGrade(new StudentGrade<>("Pok", "Wood", 10));
        student.addGrade(new StudentGrade<>("Pook", "Wood", 20));
        student.addGrade(new StudentGrade<>("Pokk", "Wood", 30));
        int avgResult = student.averageGrade("Wood");
        assertEquals(20, avgResult);
    }

    @Test
    @DisplayName("Проверка рассчета средней оценки из 1-го студента")
    public void checkAvgOneStudent() throws InvalidGradeException {
        GradeService<Integer> student = new GradeService<>();
        student.addGrade(new StudentGrade<>("Pok", "Wood", 10));
        assertThrows(IllegalArgumentException.class, () -> student.averageGrade("Wdod"));
    }
}
