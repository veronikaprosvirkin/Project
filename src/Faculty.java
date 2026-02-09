import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String nameOfFaculty;
    private List<Speciality> speciality = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    public Faculty(String nameOfFaculty) {
        this.nameOfFaculty = nameOfFaculty;
    }

    public List<Speciality> getSpeciality() {
        return speciality;
    }

    public List<Department> getDepartments() {
        return departments;
    }


    public void setSpeciality(List<Speciality> specialities) {
        this.speciality = specialities;
    }

    public String getName() { return nameOfFaculty; }

    public void setName(String newName) {
        this.nameOfFaculty = newName;
    }
}