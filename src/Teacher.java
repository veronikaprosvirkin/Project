class Teacher extends Person {
    private String position;
    private Department department;

    public Teacher(String name, String surname, String position, Department department) {
        super(name, surname);
        this.position = position;
        this.department = department;
    }

    public String getPosition() {return position;}

    @Override
    public String toString() {
        return "Teacher: " + getFullName() + " | Position: " + position + " | Department: " + department.getName();
    }

    public Department getDepartmentObject() {
        return department;
    }
}