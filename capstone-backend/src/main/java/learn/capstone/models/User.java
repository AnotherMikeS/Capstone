package learn.capstone.models;

import java.util.Objects;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private AccessType accessType;

    public User(int userId, String firstName, String lastName, AccessType accessType){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessType = accessType;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && accessType == user.accessType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, accessType);
    }
}
