package learn.capstone;

import learn.capstone.models.Audition;

public class TestHelper {

    public static Audition makeAudition3() {
        Audition audition = new Audition();
        audition.setAuditionId(3);
        audition.setAuditioneeId(2);
        audition.setPartId(2);
        return audition;
    }

    public static Audition makeInvalidAuditionee() {
        Audition audition = new Audition();
        audition.setAuditionId(3);
        audition.setAuditioneeId(0);
        audition.setPartId(2);
        return audition;
    }

    public static Audition makeInvalidRole() {
        Audition audition = new Audition();
        audition.setAuditionId(3);
        audition.setAuditioneeId(1);
        audition.setPartId(5);
        return audition;
    }

    public static Audition makeInvalidDuplicate() {
        Audition audition = new Audition();
        audition.setAuditionId(3);
        audition.setAuditioneeId(1);
        audition.setPartId(2);
        return audition;
    }

}
