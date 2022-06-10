package learn.capstone.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public enum Date {
    DATE_ONE(LocalDate.of(2022, 07, 01)),
    DATE_TWO(LocalDate.of(2022, 07, 02)),
    DATE_THREE(LocalDate.of(2022, 07, 03));

    private LocalDate date;

    private Date(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
