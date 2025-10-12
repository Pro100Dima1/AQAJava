package complexTasks;

import org.expressJava.task12.ratingsStudents.GradeService;
import org.expressJava.task12.ratingsStudents.InvalidGradeException;
import org.expressJava.task12.ratingsStudents.StudentGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeServiceTest {

    @Test
    @DisplayName("Проверка добавления студента в список")
    public void checkAddStudentInList() throws InvalidGradeException {
        GradeService<Integer> student = new GradeService<>();
        student.addGrade(new StudentGrade<>("Olya", "Chemistry", 3));
        assertEquals(1, student.returnListGrade());
    }
}
