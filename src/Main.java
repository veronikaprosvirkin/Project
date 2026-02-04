import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UniversityService service = new UniversityService();
        Scanner scanner = new Scanner(System.in);

        // Creating few students
        service.addStudent("Zbyshek Tymeckowskych", 1, 101);
        service.addStudent("Irzek Zlotych", 1, 101);

        while (true) {
            System.out.println("\n--- DigiUni (Hierarchical) ---");
            System.out.println("1. Add Student (to default Dept)");
            System.out.println("2. Find by Name");
            System.out.println("3. Find by Group");
            System.out.println("4. Find by Course");
            System.out.println("5. Show All Students");
            System.out.println("0. Exit");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {   // Creating a student
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    if (name.isBlank()) {
                        System.out.println("Empty name!");
                        break;
                    }
                    try {
                        System.out.print("Course: ");
                        int course = Integer.parseInt(scanner.nextLine());
                        System.out.print("Group: ");
                        int group = Integer.parseInt(scanner.nextLine());

                        service.addStudent(name, course, group);
                        System.out.println("Saved!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid numbers.");
                    }
                }
                case "2" -> {   // Search by name
                    System.out.print("Name part: ");
                    String q = scanner.nextLine();
                    service.findStudentsByName(q).forEach(System.out::println);
                }
                case "3" -> {   // Search by group
                    System.out.print("Group: ");
                    try {
                        int g = Integer.parseInt(scanner.nextLine());
                        service.findStudentsByGroup(g).forEach(System.out::println);
                    } catch (Exception e) { System.out.println("Error."); }
                }
                case "4" -> {   // Search by course
                    System.out.print("Course: ");
                    try {
                        int c = Integer.parseInt(scanner.nextLine());
                        service.findStudentsByCourse(c).forEach(System.out::println);
                    } catch (Exception e) { System.out.println("Error."); }
                }
                case "5" -> service.getAllStudents().forEach(System.out::println);   // Print all students
                case "0" -> { return; }     // Stop the program
                default -> System.out.println("Invalid.");  // Incorrect input
            }
        }
    }
}