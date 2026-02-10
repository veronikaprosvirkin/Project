
public class Person implements NamedEntity {
    private String name;
    private String surname;
    private String fullName;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.fullName = surname + " " + name;
    }

    @Override
    public String getName() {
        return this.fullName;
    }
    public String getSurname() {return surname;}
    public String getFullName() {return fullName;}

    public void setSurname(String newSurname) {
        this.surname = newSurname;
        this.fullName = newSurname + " " + name;
    }

    public void setName(String newName) {
        this.name = newName;
        this.fullName = surname + " " + newName;
    }
}
