class Student extends Person {
    private int course;
    private int group;
    private String faculty;

    public Student(String name) {
        super(name);
    }

    public int getCourse() {return course;}
    public int getGroup() {return group;}
    public String getFaculty() {return faculty;}
}