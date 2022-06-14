package learn.capstone.domain;

import learn.capstone.data.AuditioneeRepository;
import learn.capstone.models.Auditionee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuditioneeService {

    private final AuditioneeRepository repository;

    public AuditioneeService(AuditioneeRepository repository) {
        this.repository = repository;
    }

    public List<Auditionee> findAll() {
        return repository.findAll();
    }

    public Auditionee findById(int auditioneeId) {
        return repository.findById(auditioneeId);
    }

    public Result<Auditionee> add(Auditionee auditionee) {
        Result<Auditionee> result = validate(auditionee);
        if (!result.isSuccess()) {
            return result;
        }

        if (auditionee.getAuditioneeId() > 0) {
            boolean success = repository.update(auditionee);
            if (!success) {
                String msg = String.format("auditioneeId %s is not found", auditionee.getAuditioneeId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        } else { //add
            Auditionee withId = repository.add(auditionee);
            result.setPayload(withId);
        }
        return result;
    }

    public Result<Auditionee> update(Auditionee auditionee) {
        Result<Auditionee> result = validate(auditionee);
        if (!result.isSuccess()) {
            return result;
        }

        if (auditionee.getAuditioneeId() <= 0) {
            result.addMessage("auditionee ID is required in order to update", ResultType.INVALID);
            return result;
        }

        if (!repository.update(auditionee)) {
            String msg = String.format("Auditionee ID %s was not found.", auditionee.getAuditioneeId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Auditionee> deleteById(int auditioneeId) {
        Result<Auditionee> result = new Result<>();
        boolean success = repository.deleteById(auditioneeId);
        if (!success) {
            result.addMessage("auditionee cannot be deleted.", ResultType.INVALID);
            return result;
        }
        return result;
    }

    private Result<Auditionee> validate(Auditionee auditionee) {
        Result<Auditionee> result = new Result<>();
        if (auditionee == null) {
            result.addMessage("auditionee cannot be null", ResultType.INVALID);
            return result;
        }

        if (auditionee.getAuditioneeId() <= 0) {
            result.addMessage("auditionee ID must be greater than 0", ResultType.INVALID);
            return result;
        }

        if (auditionee.getTimeSlot() == null) {
            result.addMessage("a timeslot must be selected", ResultType.INVALID);
            return result;
        }

        if (auditionee.getSelection() == null || auditionee.getSelection().equals("")) {
            result.addMessage("you must enter the piece you will perform", ResultType.INVALID);
            return result;
        }

        if (auditionee.getUserId() <= 0) {
            result.addMessage("app user ID must be greater than 0", ResultType.INVALID);
            return result;
        }

        if (auditionee.getPartId() <= 0 || auditionee.getPartId() > 2) {
            result.addMessage("part ID must be between 1-2", ResultType.INVALID);
            return result;
        }

        return result;
    }
}


//
//        List<Auditionee> all = repository.findAll();
//        for (Auditionee a : all) {
//            if (a.getAuditioneeId() == auditionee.getAuditioneeId() && a.getPartId() == auditionee.getPartId()) {
//                result.addMessage("");
//            }

//        Set<ConstraintViolation<Auditionee>> violations = validator.validate(auditionee);
//        for (var violation : violations) {
//            result.addMessage(violation.getMessage(), ResultType.INVALID);
//        }
//        return result;
//    }