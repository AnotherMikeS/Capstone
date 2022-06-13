package learn.capstone.models;

import java.util.Objects;

public class Audition {

    private int auditionId;
    private int auditioneeId;
    private int partId;

    public Audition() {

    }

    public Audition(int auditionId, int auditioneeId, int partId) {
        this.auditionId = auditionId;
        this.auditioneeId = auditioneeId;
        this.partId = partId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audition audition = (Audition) o;
        return auditionId == audition.auditionId && auditioneeId == audition.auditioneeId && partId == audition.partId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditionId, auditioneeId, partId);
    }
}
