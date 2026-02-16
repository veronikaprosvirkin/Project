import java.util.ArrayList;
import java.util.List;

public class TeacherService {
    // Adding a teacher
    public void addTeacher(String name, String surname, String position, Department selectedDept) {
        if (!UniversityService.getUniversity().getFaculties().isEmpty() &&
                !UniversityService.getUniversity().getFaculties().get(0).getDepartments().isEmpty()) {
            // Deciding where to sign (first Faculty and first Department)
            UniversityService.getUniversity().getFaculties().get(0)
                    .getDepartments().get(0)
                    .getTeachers().add(new Teacher(name, surname, position, selectedDept));
        }
    }

    //** ===== SEARCH ===== **/
    // find all teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> allTeachers = new ArrayList<>();

        for (Faculty faculty : UniversityService.getUniversity().getFaculties()) {
            for (Department dept : faculty.getDepartments()) {
                allTeachers.addAll(dept.getTeachers());
            }
        }
        if (allTeachers.isEmpty()) {
            System.out.println("No teachers found!");
        }
        return allTeachers;
    }

    // Find teachers by name
    public List<Teacher> findTeachersByFullName(String namePart) {
        List<Teacher> result = new ArrayList<>();

        for (Faculty f : UniversityService.getUniversity().getFaculties()) {
            for (Department d : f.getDepartments()) {
                for (Teacher t : d.getTeachers()) {
                    if (t.getFullName().toLowerCase().contains(namePart.toLowerCase())) {
                        result.add(t);
                    }
                }
            }
        }
        if (result.isEmpty()) {
            System.out.println("No teacher found by name " + namePart);
        }
        return result;
    }

    public List<Teacher> getTeachersByDepartment(Department department) {
        return department.getTeachers();
    }

    public void deleteTeacher(Teacher teacher, Department department) {

        boolean removed = department.getTeachers().remove(teacher);

        if (removed) {
            System.out.println("Teacher " + teacher.getFullName() + " removed from " + department.getName());
        } else {
            System.out.println("Error: Teacher not found in this department.");
        }
    }
}
