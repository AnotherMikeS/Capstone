package learn.capstone.domain;

import learn.capstone.data.UserRepository;
import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.User;

import java.util.List;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    public Result<User> add(User user) {
        Result<User> result = validation(user);
        if (user.getUserId() !=0) {
            result.addMessage("User ID must be 0 when adding a user", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        user = repository.add(user);
        result.setPayload(user);
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = validation(user);
        if (user.getUserId() <= 0) {
            result.addMessage("User ID is required to update a user", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(user)) {
            String msg = String.format("User ID: %s was not found.", user.getUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<User> deleteById(int userId) {
        Result<User> result = new Result<>();
        boolean success = repository.deleteById(userId);
        if (!success) {
            result.addMessage("Unable to delete user.", ResultType.INVALID);
            return result;
        }

        return result;
    }

    private Result<User> validation(User user) {
        Result<User> result = new Result<>();

        /*
        Validation Rules:
            Fields can’t be null
         */

        if(user.getFirstName().isEmpty() || user.getFirstName().isBlank()){
            result.addMessage("User's first name is required", ResultType.INVALID);
        }

        if(user.getLastName().isEmpty() || user.getLastName().isBlank()){
            result.addMessage("User's last name is required", ResultType.INVALID);
        }

        if(user.getAccessType() == null){
            result.addMessage("User's access type is required", ResultType.INVALID);
        }

        return result;
    }
}

