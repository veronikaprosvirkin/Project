
public class Person implements NamedEntity {
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getName() {
        return this.name+" "+ this.surname;
    }
    public String getSurname() {return surname;}
    public String getOnlyName() {return this.name;}
    public String getFullName() {return this.name+" "+ this.surname;}

    public void setSurname(String newSurname) {
        this.surname = newSurname;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
