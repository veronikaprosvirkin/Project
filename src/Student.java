public class Student extends Person {
    private int course;
    private int group;
    private String faculty;
    private String department;

    // Update constructor to accept context
    public Student(String name, int course, int group, String faculty, String department) {
        super(name);
        this.course = course;
        this.group = group;
        this.faculty = faculty;
        this.department = department;
    }

    public int getCourse() { return course; }
    public int getGroup() { return group; }

    @Override
    public String toString() {
        return getName() + " | Course: " + course + " | Group: " + group +
                " | Faculty: " + faculty + " | Dept: " + department;
    }
}