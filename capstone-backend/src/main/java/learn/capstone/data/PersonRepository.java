package learn.capstone.data;

import learn.capstone.models.Person;
import java.util.List;

public interface PersonRepository {
        List<Person> findAll();
        Person findById(int appUserId);
        Person add(Person person);
        boolean update(Person person);
        boolean deleteById(int id);
}
