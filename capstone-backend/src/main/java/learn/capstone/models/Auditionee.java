package learn.capstone.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Auditionee {

    private int auditioneeId;

    @NotNull(message = "user ID is required")
    @NotBlank(message = "user ID is required")
    private int userId;

    @NotNull(message = "part ID is required")
    @NotBlank(message = "part ID is required")
    private int partId;

    private String timeSlot;
    private String selection;

    public Auditionee(int auditioneeId, int userId, int partId, String timeSlot, String selection){
        this.auditioneeId = auditioneeId;
        this.userId = userId;
        this.partId = partId;
        this.timeSlot = timeSlot;
        this.selection = selection;
    }

    public Auditionee() {

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

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
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
