package learn.capstone.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static learn.capstone.models.Date.*;

public class Auditionee {

    private int auditioneeId;

    @NotNull(message = "user ID is required")
    @NotBlank(message = "user ID is required")
    private int userId;

    @NotNull(message = "part ID is required")
    @NotBlank(message = "part ID is required")
    private int partId;

    public Date date;
    private String selection;

    public Auditionee(int auditioneeId, int userId, int partId, Date date, String selection) {
        this.auditioneeId = auditioneeId;
        this.userId = userId;
        this.partId = partId;
        this.date = date;
        this.selection = selection;
    }

    public int getAuditioneeId() {
        return auditioneeId;
    }

    public void setAuditioneeId(int auditioneeId) {
        this.auditioneeId = auditioneeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
