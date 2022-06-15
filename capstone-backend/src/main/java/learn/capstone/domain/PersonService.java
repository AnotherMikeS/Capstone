package learn.capstone.domain;

import learn.capstone.data.PersonRepository;
import learn.capstone.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(int userId) {
        return repository.findById(userId);
    }

    public Result<Person> add(Person user) {
        Result<Person> result = validation(user);
        if (user.getPersonId() != 0) {
            result.addMessage("Person ID must be 0 when adding a person", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        user = repository.add(user);
        result.setPayload(user);
        return result;
    }

    public Result<Person> update(Person user) {
        Result<Person> result = validation(user);
        if (user.getPersonId() <= 0) {
            result.addMessage("Person ID is required to update a person", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(user)) {
            String msg = String.format("Person ID: %s was not found.", user.getPersonId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Person> deleteById(int userId) {
        Result<Person> result = new Result<>();
        boolean success = repository.deleteById(userId);
        if (!success) {
            result.addMessage("Unable to delete Person.", ResultType.INVALID);
            return result;
        }

        return result;
    }

    private Result<Person> validation(Person person) {
        Result<Person> result = new Result<>();

        /*
        Validation Rules:
            Fields can’t be null
         */

        if(person.getFirstName() == null ||person.getFirstName().isEmpty() || person.getFirstName().isBlank()){
            result.addMessage("First name is required", ResultType.INVALID);
        }

        if(person.getLastName() == null ||person.getLastName().isEmpty() || person.getLastName().isBlank()){
            result.addMessage("Last name is required", ResultType.INVALID);
        }

        if(person.getAppUserId() <= 0 ){
            result.addMessage("Valid User Id is required", ResultType.INVALID);
        }

        return result;
    }
}
