import java.util.Collection;
import java.util.List;

public class DepartmentService {
    public List<Department> getDepartments(Faculty faculty) {
        return faculty.getDepartments();
    }

    public void addNewDepartment(String newDepartmentName, Faculty selectedFaculty) {
        boolean exists = selectedFaculty.getDepartments().stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(newDepartmentName));

        if (exists) {
            System.out.println("Error: Department with this name already exists!");
            return;
        }
        selectedFaculty.getDepartments().add(new Department(newDepartmentName));
        System.out.println("Department created successfully!");
    }

    public void editDepartmentName(Department dept, String editName, Faculty faculty) {
        if (isNameDuplicate(faculty.getDepartments(), editName, Department::getName)) {
            System.out.println("Error: Department with name '" + editName + "' already exists on this faculty.");
            return;
        }

        dept.setName(editName);
        System.out.println("Department name updated successfully to: " + editName);
    }
    private <T> boolean isNameDuplicate(Collection<T> list, String newName, java.util.function.Function<T, String> nameExtractor) {
        return list.stream()
                .anyMatch(item -> nameExtractor.apply(item).equalsIgnoreCase(newName));
    }

    public void deleteDepartment(Department selectedDept, Faculty selectedFaculty) {
        selectedFaculty.getDepartments().remove(selectedDept);
    }
}
