package learn.capstone.domain;

import learn.capstone.data.AppUserRepository;
import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return user;
    }

    public List<AppUser> findAll() {
        return repository.findAll();
    }

    public AppUser findById(int userId) {
        return repository.findById(userId);
    }
    public AppUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Result<AppUser> add(AppUser user) {
        Result<AppUser> result = validation(user);
        if (user.getAppUserId() != 0) {
            result.addMessage("User ID must be 0 when adding a user", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        user = repository.add(user);
        result.setPayload(user);
        return result;
    }

    public Result<AppUser> update(AppUser user) {
        Result<AppUser> result = validation(user);
        if (user.getAppUserId() <= 0) {
            result.addMessage("User ID is required to update a user", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(user)) {
            String msg = String.format("User ID: %s was not found.", user.getAppUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<AppUser> deleteById(int userId) {
        Result<AppUser> result = new Result<>();
        boolean success = repository.deleteById(userId);
        if (!success) {
            result.addMessage("Unable to delete user.", ResultType.INVALID);
            return result;
        }

        return result;
    }

    private Result<AppUser> validation(AppUser user) {
        Result<AppUser> result = new Result<>();

        /*
        Validation Rules:
            Fields can’t be null
         */

        if(user.getUsername() == null || user.getUsername().isEmpty() || user.getUsername().isBlank()){
            result.addMessage("Username is required", ResultType.INVALID);
        }

        if(user.getFirstName() == null ||user.getFirstName().isEmpty() || user.getFirstName().isBlank()){
            result.addMessage("First name is required", ResultType.INVALID);
        }

        if(user.getLastName() == null ||user.getLastName().isEmpty() || user.getLastName().isBlank()){
            result.addMessage("Last name is required", ResultType.INVALID);
        }

        if(user.getPassword() == null ||user.getPassword().isEmpty() || user.getPassword().isBlank()){
            result.addMessage("Password is required", ResultType.INVALID);
        }

        return result;
    }
}

