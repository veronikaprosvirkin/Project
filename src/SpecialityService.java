import java.util.Collection;

public class SpecialityService {
    private University university;

    public SpecialityService(University university) {
        this.university = university;
    }

    private <T> boolean isNameDuplicate(Collection<T> list, String newName, java.util.function.Function<T, String> nameExtractor) {
        return list.stream()
                .anyMatch(item -> nameExtractor.apply(item).equalsIgnoreCase(newName));
    }

    public void addNewSpeciality(String newSpecialityName, Faculty selectedFaculty) {
        boolean exists = selectedFaculty.getSpeciality().stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(newSpecialityName));

        if (exists) {
            System.out.println("Error: Speciality with this name already exists!");
            return;
        }
        selectedFaculty.getSpeciality().add(new Speciality(newSpecialityName));
        System.out.println("Speciality created successfully!");
    }

    public void editSpecialityName(Speciality speciality, String editName, Faculty faculty) {
        if (isNameDuplicate(faculty.getSpeciality(), editName, Speciality::getName)) {
            System.out.println("Error: Department with name '" + editName + "' already exists on this faculty.");
            return;
        }

        speciality.setName(editName);
        System.out.println("Department name updated successfully to: " + editName);
    }


    public void deleteSpeciality(Speciality selectedSpeciality, Faculty selectedFaculty) {
        selectedFaculty.getSpeciality().remove(selectedSpeciality);
    }
}
