package learn.capstone;

import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.Person;
import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
//import learn.capstone.models.AppUser;

public class TestHelper {

    public static Person makeLuigi() {
        Person newUser = new Person(5, 5, "Luigi", "Luigi");
        return newUser;
    }
    public static Person makeMike() {
        Person newUser = new Person(2, 2, "Mike", "Smith");
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

    public static Auditionee makeValidAuditionee2() {
        Auditionee auditionee = new Auditionee();
        auditionee.setUserId(5);
        auditionee.setPartId(2);
        auditionee.setTimeSlot("2022-07-02 1:20pm");
        auditionee.setSelection("Barefoot in the Park monologue");
        return auditionee;
    }

    public static Auditionee makeValidAuditionee3() {
        Auditionee auditionee = new Auditionee();
        auditionee.setUserId(5);
        auditionee.setPartId(2);
        auditionee.setTimeSlot("2022-07-02 1:40pm");
        auditionee.setSelection("Odyssey monologue");
        return auditionee;
    }

    public static Auditionee makeValidAuditionee4() {
        Auditionee auditionee = new Auditionee();
        auditionee.setUserId(5);
        auditionee.setPartId(1);
        auditionee.setTimeSlot("2022-07-02 2:00pm");
        auditionee.setSelection("Bring Him Home");
        return auditionee;
    }

    public static Auditionee makeValidAuditionee5() {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(6);
        auditionee.setUserId(5);
        auditionee.setPartId(1);
        auditionee.setTimeSlot("2022-07-02 2:20pm");
        auditionee.setSelection("Circus");
        return auditionee;
    }

    public static Auditionee makeValidAuditionee6() {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(0);
        auditionee.setUserId(6);
        auditionee.setPartId(1);
        auditionee.setTimeSlot("2022-07-02 2:20pm");
        auditionee.setSelection("Oh What A Beautiful Morning");
        return auditionee;
    }

    public static Auditionee makeValidAuditionee7() {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(1);
        auditionee.setUserId(6);
        auditionee.setPartId(1);
        auditionee.setTimeSlot("2022-07-02 3:20pm");
        auditionee.setSelection("Balloon Animals");
        return auditionee;
    }

    public static Auditionee makeInvalidAuditionee1() {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(7);
        auditionee.setUserId(6);
        auditionee.setPartId(1);
        auditionee.setTimeSlot("2022-07-02 2:40pm");
        auditionee.setSelection("Test song 1");
        return auditionee;
    }




}
