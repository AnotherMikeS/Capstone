package learn.capstone.models;

import java.util.Objects;

public class Audition {

    private int auditionId;
    private int auditioneeId;
    private String firstName;
    private String lastName;
    private int partId;
    private String role;

    public Audition() {

    }

    public Audition(int auditionId, int auditioneeId, String firstName, String lastName, int partId, String role) {
        this.auditionId = auditionId;
        this.auditioneeId = auditioneeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.partId = partId;
        this.role = role;
    }


    public int getAuditionId() {
        return auditionId;
    }

    public void setAuditionId(int auditionId) {
        this.auditionId = auditionId;
    }

    public int getAuditioneeId() {
        return auditioneeId;
    }

    public void setAuditioneeId(int auditioneeId) {
        this.auditioneeId = auditioneeId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audition audition = (Audition) o;
        return auditionId == audition.auditionId && auditioneeId == audition.auditioneeId && partId == audition.partId && Objects.equals(firstName, audition.firstName) && Objects.equals(lastName, audition.lastName) && Objects.equals(role, audition.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditionId, auditioneeId, firstName, lastName, partId, role);
    }
}
