package learn.capstone.models;

import java.util.Objects;

public class Person {
    private int personId;
    private int appUserId;
    private String firstName;
    private String lastName;

    public Person(int personId, int appUserId, String firstName, String lastName) {
        this.personId = personId;
        this.appUserId = appUserId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {

    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && appUserId == person.appUserId && firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, appUserId, firstName, lastName);
    }
}
