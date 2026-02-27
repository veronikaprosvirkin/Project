import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class StudentServiceTest {
    private StudentService studentService;
    private University university;
    private Speciality speciality;
    private Faculty faculty;

    @BeforeEach
    void setUp() {
        university = new University();
        studentService = new StudentService(university);
        faculty = new Faculty("Faculty of Computer Science");
        speciality = new Speciality("Software Engineering");
        faculty.getSpeciality().add(speciality);
        university.getFaculties().add(faculty);
    }

    @Test
    void addStudent() {
        Student student = new Student("Ivan", "Sirko", 1, 101, "IT", speciality);
        studentService.addStudentToSpeciality(student, speciality, 101);
        List<Student> allStudents = studentService.getAllStudents();
        assertTrue(allStudents.contains(student));
    }
}