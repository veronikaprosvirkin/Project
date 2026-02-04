import java.util.ArrayList;
import java.util.List;

public class Speciality {
    private String nameOfSpeciality;
    private List<Student> students= new ArrayList<>();

    public Speciality(String nameOfSpeciality) {
        this.nameOfSpeciality = nameOfSpeciality;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() { return nameOfSpeciality; }
}