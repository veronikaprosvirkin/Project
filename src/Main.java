import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityService service = new UniversityService();
        Scanner scanner = new Scanner(System.in);

        // Creating few students
        service.addStudent("Zbyshek", "Tymekowskych", 1, 101);
        service.addStudent("Irzek", "Zlotych", 1, 101);
        service.addStudent("Irzek", "Tymekowskych", 2, 15);

        while (true) {
            System.out.println("\n--- DigiUni ---");
            System.out.println("1. Work with Faculties"); // finished
            System.out.println("2. Work with Departments"); //finished
            System.out.println("3. Work with Specialities"); //finished
            System.out.println("4. Work with Students"); //logic written, not finished realization
            System.out.println("5. Work with Teachers");
            System.out.println("6. Search");
            System.out.println("0. Exit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> { // Work with faculties
                    System.out.println("1. Add Faculty");
                    System.out.println("2. Manage Existing Faculty");
                    System.out.println("0. Back");
                    int action = InputUtils.readInt(scanner, "> ", 0, 2);

                    if (action == 1) {
                        facultyAddFaculty(scanner, service);
                    } else if (action == 2) { //manage existing faculties
                        Faculty selectedFaculty = selectFaculty(scanner, service);
                        if (selectedFaculty == null) break;
                        System.out.println("1. Edit Faculty");
                        System.out.println("2. Delete Faculty");
                        System.out.println("0. Back");
                        int workWithFaculty = InputUtils.readInt(scanner, "> ", 0, 2);
                        if (workWithFaculty == 1) { //edit faculty name
                            facultyManageExistingFacultyRename(scanner, service, selectedFaculty);
                        } else if (workWithFaculty == 2) { //delete faculty
                            facultyManageExistingFacultyDelete(scanner, service, selectedFaculty);
                        }
                    }
                }


                case "2" -> {// Work with departments
                    System.out.println("1. Add Department");
                    System.out.println("2. Manage existing Department");
                    System.out.println("0. Back");
                    int action = InputUtils.readInt(scanner, "> ", 0, 2);

                    if (action == 1) { // add a new department
                        departmentAddDepartment(scanner, service);
                    } else if (action ==2) { // manage existing department
                        // Select Faculty and Department
                        Faculty selectedFaculty = selectFaculty(scanner, service);
                        if (selectedFaculty == null) break;
                        Department selectedDept = selectDepartment(scanner, selectedFaculty);
                        if (selectedDept == null) break;

                        //Work with a selected department
                        System.out.println("\nDepartment: " + selectedDept.getName());
                        System.out.println("1. Edit name of the Department");
                        System.out.println("2. Delete Department");
                        System.out.println("3. Show all Teachers in the Department");
                        System.out.println("0. Back");
                        int workWithDepartment = InputUtils.readInt(scanner, "> ", 0, 3);

                        if (workWithDepartment ==1){ // edit department name
                            departmentRenameDepartment(scanner, service, selectedDept, selectedFaculty);
                        } else if (workWithDepartment == 2) { //delete department
                            departmentDeleteDepartment(scanner, service, selectedDept, selectedFaculty);
                        } else if (workWithDepartment == 3) { //show all teachers in the department
                            departmentShowTeachers(service, selectedDept);
                        }
                    }
                }
                case "3" -> {   //? Edit speciality
                    System.out.println("1. Add Speciality");
                    System.out.println("2. Manage existing Speciality");
                    System.out.println("0. Back");
                    int action = InputUtils.readInt(scanner, "> ", 0, 2);

                    if (action == 1) {
                        specialityAddSpeciality(scanner, service);
                    } else if (action == 2) {
                        // Select Faculty and Speciality
                        Faculty selectedFaculty = selectFaculty(scanner, service);
                        if (selectedFaculty == null) break;
                        Speciality selectedSpeciality = selectSpeciality(scanner, selectedFaculty);
                        if (selectedSpeciality == null) break;

                        System.out.println("1. Rename Speciality");
                        System.out.println("2. Delete Speciality");
                        System.out.println("0. Back");

                        int workWithSpeciality = InputUtils.readInt(scanner, "> ", 0, 2);
                        if (workWithSpeciality == 1) {
                            specialityRenameSpeciality(scanner, service, selectedSpeciality, selectedFaculty);
                        } else if (workWithSpeciality == 2) {
                            specialityDeleteSpeciality(scanner, service, selectedSpeciality, selectedFaculty);
                        }
                    }

                }
                case "4" -> {// Work with students
                    System.out.println("1. Add Student");
                    System.out.println("2. Delete Student");
                    System.out.println("3. Edit information about student");
                    System.out.println("4. Show all");
                    System.out.println("0. Back");

                    int workWithStudent = InputUtils.readInt(scanner, "> ", 0, 4);

                    if (workWithStudent == 1){ //add student
                        studentAddStudent(scanner, service);
                    }
                    else if (workWithStudent == 2){ //delete student
                        System.out.println("--- Delete Student ---");
                        System.out.print("1. Delete by full name: ");
                        System.out.println("2. Delete by ID: ");
                        System.out.print("0. Back: ");
                        int deleteStudent = InputUtils.readInt(scanner, "> ", 0, 2);

                        if (deleteStudent == 1){
                            studentDeleteByName(scanner, service);
                        }
                        else if (deleteStudent == 2){
                            studentDeleteById(scanner, service);
                        }

                    } else if (workWithStudent == 3) { //edit student
                        System.out.println("--- Edit Student ---");
                        System.out.println("1. Edit by full name");
                        System.out.println("2. Edit by ID");
                        System.out.println("0. Back");
                        int editStudent = InputUtils.readInt(scanner, "> ", 0, 2);
                        if (editStudent == 1) {
                            studentEditByName(scanner, service);
                        }
                        else if (editStudent == 2){
                            studentEditById(scanner, service);
                        }


                    } else if (workWithStudent == 4) {  //show all students
                        studentShowAll(service);
                    }

                }
                case "5" -> {
                    System.out.println("1. Add Teacher");
                    System.out.println("2. Delete Teacher");
                    System.out.println("3. Edit information about teacher");
                    System.out.println("4. Show all");
                    System.out.println("0. Back");
                    int workWithTeacher = InputUtils.readInt(scanner, "> ", 0, 4);

                    if (workWithTeacher == 1){ //add teacher
                        teacherAddTeacher(scanner, service);
                    }
                    else if (workWithTeacher == 2){
                        System.out.println(" --- Delete Teacher ---");
                        System.out.println("1. Delete by full name");
                        System.out.println("2. Add by ID");
                        System.out.println("0. Cancel");
                        int deleteTeacher = InputUtils.readInt(scanner, "> ", 0, 2);
                        if (deleteTeacher == 1){
                            teacherDeleteByName(scanner, service);
                        } else  if (deleteTeacher == 2){
                            teacherDeleteById(scanner, service);
                        }

                    } else if (workWithTeacher == 3) { //edit teacher
                        System.out.print("1. Edit by full name: ");
                        System.out.println("2. Edit by ID: ");
                        System.out.print("0. Back: ");
                        int editTeacher = InputUtils.readInt(scanner, "> ", 0, 2);
                        if (editTeacher == 1) {
                            teacherEditByName(scanner, service);
                        }
                        else if (editTeacher == 2){
                            teacherDeleteById(scanner, service);
                        }
                    } else if (workWithTeacher == 4) { //show all
                        teacherShowAll(service);
                    }
                }

                case "6" -> {   //search
                    System.out.println("1. Find Student: ");
                    System.out.println("2. Find Teacher: ");
                    int searchType = InputUtils.readInt(scanner, "> ", 1, 2);
                    if (searchType == 1) { //Find Student
                        System.out.println("1. Search by full name: ");
                        System.out.println("2. Search by group number: ");
                        System.out.println("3. Search by course: ");
                        System.out.println("4. Search by speciality: ");
                        System.out.println("0. Back: ");
                        int searchBy = InputUtils.readInt(scanner, "> ", 0, 4);

                        if (searchBy == 1) { //by full name
                            String name = InputUtils.readLine(scanner, "Enter full name: ", false, true);
                            List<Student> result = service.findStudentsByFullName(name);
                            if (result.isEmpty()) {
                                System.out.println("No students found with this name.");
                            } else {
                                result.forEach(System.out::println);
                            }
                        }
                        else if (searchBy == 2) { //by group number

                            System.out.println("1. Find in specific speciality");
                            System.out.println("2. Find in all university");

                            int type = InputUtils.readInt(scanner, "> ", 1, 2);

                            if (type == 1) {    // Search in specific speciality

                                Faculty selectedFaculty = selectFaculty(scanner, service);
                                if (selectedFaculty == null) break;

                                Speciality selectedSpeciality = selectSpeciality(scanner, selectedFaculty);
                                if (selectedSpeciality == null) break;

                                int groupNumber = InputUtils.readInt(scanner, "Enter Group number: ", 1, Integer.MAX_VALUE);

                                List<Student> results = service.findStudentsInSpecialityByGroup(selectedSpeciality, groupNumber);

                                if (results.isEmpty()) {
                                    System.out.println("No students found in group " + groupNumber + " within " + selectedSpeciality.getName());
                                } else {
                                    results.forEach(System.out::println);
                                }
                            }
                            else {      // Search in all university
                                int groupNumber = InputUtils.readInt(scanner, "Enter Group number: ", 1, Integer.MAX_VALUE);

                                List<Student> results = service.findStudentsByGroup(groupNumber);

                                if (results.isEmpty()) {
                                    System.out.println("No students found in group " + groupNumber + " in the whole university.");
                                } else {
                                    results.forEach(System.out::println);
                                }
                            }
                            } else if (searchBy == 3) { //by course
                                int course = InputUtils.readInt(scanner, "Enter course number: ", 1, 6);
                                List<Student> results = service.findStudentsByCourse(course);
                        } else if (searchBy == 4) { // by speciality
                            //not finished
                        }

                    } else if (searchType == 2) { //Find Teacher
                        System.out.println("1. Search by full name: ");
                        System.out.println("2. Search by department: ");
                        System.out.println("3. Search by position: ");
                        System.out.println("0. Back: ");

                        int searchBy = InputUtils.readInt(scanner, "> ", 0, 3);
                        if (searchBy == 1){
                            //search by full name
                        } else if (searchBy == 2) {

                        }



                    }

                }
                case "0" -> { return; }     //? Stop the program
                default -> System.out.println("Invalid.");  //? Incorrect input
            }
        }
    }

    // * ===== METHODS SEPARATED FOR EVERY ACTION ===== * //

    //! ======= WORK WITH FACULTY ===== //
    /** Add new Faculty */
    private static void facultyAddFaculty(Scanner scanner, UniversityService service) {
        String name = InputUtils.readLine(scanner, "Enter new Faculty name: ", false, true);
        service.addNewFaculty(name);
    }

    /** Rename Faculty */
    private static void facultyManageExistingFacultyRename(Scanner scanner, UniversityService service,  Faculty selectedFacultyToRename) {
        String newName = InputUtils.readLine(scanner, "Enter new Faculty name: ", false, true);
        service.editFacultyName(selectedFacultyToRename, newName);
    }

    /** Delete Faculty */
    private static void facultyManageExistingFacultyDelete(Scanner scanner, UniversityService service, Faculty selectedFacultyToDelete) {
        System.out.print("Are you sure you want to delete " + selectedFacultyToDelete.getName() + "? (y/n)");
        if (scanner.nextLine().toLowerCase().startsWith("y")) {
            service.deleteFaculty(selectedFacultyToDelete);
            System.out.println("Faculty deleted successfully!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }


    //! ======= WORK WITH DEPARTMENT ===== //
    /** Add new Department */
    private static void departmentAddDepartment(Scanner scanner, UniversityService service){
        System.out.println("Choose faculty where department will be added:");
        Faculty selectedFaculty = selectFaculty(scanner, service);
        if (selectedFaculty != null) {
            String name = InputUtils.readLine(scanner, "Enter new Department name: ", false, true);
            service.addNewDepartment(name, selectedFaculty);
        }
        else {
            System.out.println("No faculties found. Please add a new one first.");
        }
    }

    /** Rename the Department */
    private static void departmentRenameDepartment(Scanner scanner, UniversityService service, Department selectedDept, Faculty selectedFaculty) {
        String editName = InputUtils.readLine(scanner,"Write new name for " + selectedDept.getName() + ": ", false, true);
        service.editDepartmentName(selectedDept, editName, selectedFaculty);
    }

    /** Delete the Department */
    private static void departmentDeleteDepartment(Scanner scanner, UniversityService service,  Department selectedDept,  Faculty selectedFaculty) {
        System.out.print("Are you sure you want ot delete " + selectedDept.getName() + "? (y/n): ");
        if (scanner.nextLine().toLowerCase().startsWith("y")) {
            service.deleteDepartment(selectedDept, selectedFaculty);
            System.out.println("Department deleted successfully!");
        }
        else {
            System.out.println("Operation cancelled.");
        }
    }

    /** Show all teachers in the Department */
    private static void departmentShowTeachers(UniversityService service,  Department selectedDept){
        List<Teacher> teachers = service.getTeachersByDepartment(selectedDept);
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers assigned to " + selectedDept.getName() + " yet.");
        } else {
            System.out.println("\n--- Teachers in " + selectedDept.getName() + " ---");
            teachers.forEach(System.out::println);
        }
    }

    //! ======= WORK WITH SPECIALITY ===== //
    /** Add new Speciality */
    private static void specialityAddSpeciality(Scanner scanner, UniversityService service){
        System.out.println("Choose faculty where speciality will be added:");
        Faculty selectedFaculty = selectFaculty(scanner, service);
        if (selectedFaculty != null) {
            String name = InputUtils.readLine(scanner, "Enter new Speciality name: ", false, true);
            service.addNewSpeciality(name, selectedFaculty);
        }
        else {
            System.out.println("No faculties found. Please add a new one first.");
        }
    }

    /** Rename the Speciality */
    private static void specialityRenameSpeciality(Scanner scanner, UniversityService service, Speciality selectedSpeciality, Faculty selectedFaculty) {
        String editName = InputUtils.readLine(scanner,"Write new name for " + selectedSpeciality.getName() + ": ", false, true);
        service.editSpecialityName(selectedSpeciality, editName, selectedFaculty);
    }

    /** Delete the Speciality */
    private static void specialityDeleteSpeciality(Scanner scanner, UniversityService service,  Speciality selectedSpeciality,  Faculty selectedFaculty) {
        System.out.print("Are you sure you want ot delete " + selectedSpeciality.getName() + "? (y/n): ");
        if (scanner.nextLine().toLowerCase().startsWith("y")) {
            service.deleteSpeciality(selectedSpeciality, selectedFaculty);
            System.out.println("Speciality deleted successfully!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    //! ======= WORK WITH STUDENTS ===== //
    /** Add new Student */
    private static void studentAddStudent(Scanner scanner, UniversityService service){
        System.out.println("--- Add Student ---");
        Faculty selectedFaculty = selectFaculty(scanner, service);
        if (selectedFaculty == null) return;

        // Select speciality
        Speciality selectedSpeciality = selectSpeciality(scanner, selectedFaculty);
        if (selectedSpeciality == null) return;


        // Student's info
        String name = InputUtils.readLine(scanner, "Name: ", false, false);
        String surname = InputUtils.readLine(scanner, "Surname: ", false, false);
        int course = InputUtils.readInt(scanner, "Enter Course (1-6): ", 1, 6);
        int groupNumber = InputUtils.readInt(scanner, "Enter Group: ", 1, Integer.MAX_VALUE);


        // Save
        Student s = new Student(name, surname, course, groupNumber,
                selectedFaculty.getName(),
                selectedSpeciality.getName());
        service.addStudentToSpeciality(s, selectedSpeciality, groupNumber);

        System.out.println("Student " + name + " added to group " + groupNumber +
                " in " + selectedSpeciality.getName());
    }

    /** Delete the Student by name */
    private static void studentDeleteByName(Scanner scanner, UniversityService service){
        System.out.print("Enter full name part: ");
        String fullName = InputUtils.readLine(scanner, "Name: ", false, true);
        List<Student> result = service.findStudentsByFullName(fullName);

        if (result.isEmpty()) {
            System.out.println("No students found with this name.");
        } else {
            if (result.size() > 1) {
                System.out.println("Multiple students found. Please select one: ");
                for (int i = 0; i < result.size(); i++) {
                    System.out.println((i + 1) + ". " + result.get(i).getFullName() +
                            " (Group: " + result.get(i).getGroup() + ", Course: " + result.get(i).getCourse() + ")");
                }
                System.out.println("0. Cancel");
                int index = InputUtils.readInt(scanner, "> ", 0, result.size());
                if (index == 0) {return;}

                Student student = result.get(index - 1);
                service.deleteStudent(student);   // NOT FINISHED METHOD
            }
        }
    }

    /** Delete the Student by ID */
    private static void studentDeleteById(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Edit the Student by name */
    private static void studentEditByName(Scanner scanner, UniversityService service){
        String fullName = InputUtils.readLine(scanner, "Enter full name part: ", false, true);
        List<Student> result = service.findStudentsByFullName(fullName);

        if (result.isEmpty()) {
            System.out.println("No students found with this name.");
        } else {
            Student studentToProcess;
            if (result.size() > 1) {
                System.out.println("Multiple students found. Please select one: ");
                for (int i = 0; i < result.size(); i++) {
                    System.out.println((i + 1) + ". " + result.get(i).getFullName() +
                            " (Group: " + result.get(i).getGroup() + ", Course: "+ result.get(i).getCourse() + ")");
                }
                int index = InputUtils.readInt(scanner, "> ", 1, result.size());

                Student student = result.get(index - 1);

                System.out.println("\nEditing student: " + student.getFullName());
                System.out.println("1. Change Surname");
                System.out.println("2. Change Course");
                System.out.println("3. Change Group");
                System.out.println("0. Cancel");

                int fieldChoice = InputUtils.readInt(scanner, "> ", 0, 3);

                switch (fieldChoice) {
                    case 1 -> {
                        String newSurname = InputUtils.readLine(scanner, "Enter new surname: ", false, false);
                        student.setSurname(newSurname);
                        System.out.println("Surname updated!");
                    }
                    case 2 -> {
                        int newCourse = InputUtils.readInt(scanner, "Enter new course (1-6): ", 1, 6);
                        student.setCourse(newCourse);
                        System.out.println("Course updated!");
                    }
                    case 3 -> {
                        int newGroup = InputUtils.readInt(scanner, "Enter new group number: ", 1, Integer.MAX_VALUE);
                        //not finished
                        service.moveStudentToGroup(student, newGroup);
                        System.out.println("Group updated and student moved!");
                    }
                }
            }
        }
    }

    /** Edit the Student by ID */
    private static void studentEditById(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Show all students */
    private static void studentShowAll(UniversityService service){
        List<Student> result = service.getAllStudents();
        if (result.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println(" --- Students ---");
            result.forEach(System.out::println);
        }
    }

    //! ======= WORK WITH TEACHERS ===== //
    /** Add new Teacher */
    private static void teacherAddTeacher(Scanner scanner, UniversityService service){
        System.out.println("--- Add Teacher ---");
        Faculty selectedFaculty = selectFaculty(scanner, service);
        if (selectedFaculty == null) return;

        // Select Department
        Department selectedDept = selectDepartment(scanner, selectedFaculty);
        if (selectedDept == null) return;


        // Teachers's info
        String name = InputUtils.readLine(scanner, "Name: ", false, false);
        String surname = InputUtils.readLine(scanner, "Surname: ", false, false);
        String position = InputUtils.readLine(scanner, "Position: ", false, true);



        // Save
        Teacher t = new Teacher(name,surname,position);
        service.addTeacher(name, surname, position);
        System.out.println("Teacher " + name + " " + surname +
                " successfully added to department: " + selectedDept.getName());
    }

    /** Delete the Teacher by name */
    private static void teacherDeleteByName(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Delete the Teacher by ID */
    private static void teacherDeleteById(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Edit the Teacher by name */
    private static void teacherEditByName(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Edit the Teacher by ID */
    private static void teacherEditById(Scanner scanner, UniversityService service){
        // NOT FINISHED METHOD
    }

    /** Show all teachers */
    private static void teacherShowAll(UniversityService service){
        List<Teacher> result = service.getAllTeachers();
        if (result.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println(" --- Teachers ---");
            result.forEach(System.out::println);
        }
    }





    // * ===== METHODS HELPERS ===== * //

    /**
     * ? Faculty selection
     * @param scanner provided
     * @param service provided
     * @return Faculty
     */
    private static Faculty selectFaculty(Scanner scanner, UniversityService service) {
        List<Faculty> faculties = service.getFaculties();
        if (faculties.isEmpty()) {
            System.out.println("No faculties available!");
            return null;
        }
        System.out.println("--- Choose Faculty ---");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.println("0. Cancel");
        int index = InputUtils.readInt(scanner, "> ", 0, faculties.size());
        if (index == 0) {return null;}
        return faculties.get(index-1);
    }

    /**
     * ? Speciality selection
     * @param scanner provided
     * @param faculty provided
     * @return Speciality
     */
    private static Speciality selectSpeciality(Scanner scanner, Faculty faculty) {
        List<Speciality> specialities = faculty.getSpeciality();
        if (specialities.isEmpty()) {
            System.out.println("No specialities in this faculty!");
            return null;
        }
        System.out.println("--- Choose Speciality ---");
        for (int i = 0; i < specialities.size(); i++) {
            System.out.println((i + 1) + ". " + specialities.get(i).getName());
        }
        System.out.println("0. Cancel");
        int index = InputUtils.readInt(scanner, "> ", 0, specialities.size());
        if (index == 0) {return null;}
        return specialities.get(index-1);
    }

    /**
     * ? Department selection
     * @param scanner provided
     * @param faculty provided
     * @return Department
     */
    private static Department selectDepartment(Scanner scanner, Faculty faculty) {

        List<Department> departments = faculty.getDepartments();

        if (departments.isEmpty()) {
            System.out.println("No departments found in faculty: " + faculty.getName());
            return null;
        }

        System.out.println("\n--- Choose Department from " + faculty.getName() + " ---");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println((i + 1) + ". " + departments.get(i).getName());
        }
        System.out.println("0. Cancel");

        int index = InputUtils.readInt(scanner, "> ", 0, departments.size());
        if (index == 0) return null;
        return departments.get(index - 1);
    }
}