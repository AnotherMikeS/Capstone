package learn.capstone;

import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
import learn.capstone.models.AppUser;

public class TestHelper {

    public static AppUser makeUser() {
        AppUser newUser = new AppUser(5, "hamilton", "Lin-Manuel", "Miranda", "alexander");
        return newUser;
    }

    public static Result makeResult(String... messages) {
        Result result = new Result();
        for (String message : messages) {
            result.addMessage(message, ResultType.INVALID);
        }
        return result;
    }

    public static Audition makeValidAudition(int auditionId) {
        Audition audition = new Audition();
        audition.setAuditionId(auditionId);
        audition.setAuditioneeId(4);
        audition.setPartId(1);
        return audition;
    }

    public static Audition makeAudition1() {
        Audition audition = new Audition();
        audition.setAuditionId(1);
        audition.setAuditioneeId(1);
        audition.setPartId(1);
        return audition;
    }

    public static Audition makeAudition2() {
        Audition audition = new Audition();
        audition.setAuditionId(2);
        audition.setAuditioneeId(2);
        audition.setPartId(2);
        return audition;
    }

    public static Audition makeAudition3() {
        Audition audition = new Audition();
        audition.setAuditionId(3);
        audition.setAuditioneeId(3);
        audition.setPartId(1);
        return audition;
    }

    public static Audition makeAudition3Test() {
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

    public static Auditionee makeValidAuditionee() {
        Auditionee auditionee = new Auditionee();
        auditionee.setUserId(4);
        auditionee.setPartId(2);
        auditionee.setTimeSlot("2022-07-02 1:00pm");
        auditionee.setSelection("I Dreamed A Dream");
        return auditionee;
    }

}
