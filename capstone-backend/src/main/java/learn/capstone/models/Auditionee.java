package learn.capstone.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Auditionee {

//        - Id
//        - User Id (not null)
//        - Part Id (not null)
//        - Date (ENUM)
//        - Selection

    private int auditioneeId;

    @NotNull(message = "user ID is required")
    @NotBlank(message = "user ID is required")
    private int userId;

    @NotNull(message = "part ID is required")
    @NotBlank(message = "part ID is required")
    private int partId;

    private enum date {DATE_ONE, DATE_TWO, DATE_THREE};

    private String selection;

    public int getAuditioneeId() {
        return auditioneeId;
    }

    public void setAuditioneeId(int auditioneeId) {
        auditioneeId = auditioneeId;
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

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
