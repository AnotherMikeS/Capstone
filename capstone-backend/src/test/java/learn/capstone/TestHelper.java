package learn.capstone;

import learn.capstone.models.AccessType;
import learn.capstone.models.Audition;
import learn.capstone.models.User;

public class TestHelper {

    public User makeUser(){
        User newUser = new User(5, "Lin-Manuel", "Miranda", AccessType.AUDITIONEE);
        return newUser;
    }
}
