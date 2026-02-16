import java.util.Collection;
import java.util.List;

public class FacultyService {
    private University university;

    public FacultyService(University university) {
        this.university = university;
    }

    public List<Faculty> getFaculties() {
        return university.getFaculties();
    }

    private <T> boolean isNameDuplicate(Collection<T> list, String newName, java.util.function.Function<T, String> nameExtractor) {
        return list.stream().anyMatch(item -> nameExtractor.apply(item).equalsIgnoreCase(newName));
    }

    public void addNewFaculty(String name) {
        if (isNameDuplicate(university.getFaculties(), name, Faculty::getName)) {
            System.out.println("Error: Faculty with name '" + name + "' already exists.");
            return;
        }
        university.getFaculties().add(new Faculty(name));
        System.out.println("Faculty added successfully.");
    }

    public void deleteFaculty(Faculty selectedFacultyToDelete) {
        university.getFaculties().remove(selectedFacultyToDelete);
    }

    public void editFacultyName(Faculty faculty, String newName) {
        if (isNameDuplicate(university.getFaculties(), newName, Faculty::getName)) {
            System.out.println("Error: Faculty with name '" + newName + "' already exists.");
            return;
        }
        faculty.setName(newName);
        System.out.println("Faculty name updated successfully to: " + newName);
    }
}
