package learn.capstone.domain;

import learn.capstone.data.AuditionRepository;
import learn.capstone.models.Audition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditionService {

    private final AuditionRepository repository;

    public AuditionService(AuditionRepository repository) {
        this.repository = repository;
    }

    public List<Audition> findAll() {
        return repository.findAll();
    }

    public Audition findById(int auditionId) {
        return repository.findById(auditionId);
    }

    public Result<Audition> add(Audition audition) {
        Result<Audition> result = validate(audition);
        if (!result.isSuccess()) {
            return result;
        }

        if (audition.getAuditionId() !=0) {
            result.addMessage("Audition ID cannot be set for 'add' operation.", ResultType.INVALID);
            return result;
        }

        audition = repository.add(audition);
        result.setPayload(audition);
        return result;
    }

    public Result<Audition> update(Audition audition) {
        Result<Audition> result = validate(audition);
        if (!result.isSuccess()) {
            return result;
        }

        if (audition.getAuditionId() <= 0) {
            result.addMessage("Audition ID must be set for 'update' operation.", ResultType.INVALID);
            return result;
        }

        if (!repository.update(audition)) {
            String msg = String.format("Audition ID: %s was not found.", audition.getAuditionId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Audition> deleteById(int auditionId) {
        Result<Audition> result = new Result<>();
        boolean success = repository.deleteById(auditionId);
        if (!success) {
            result.addMessage("Unable to delete audition.", ResultType.INVALID);
            return result;
        }

        return result;
    }

    private Result<Audition> validate(Audition audition) {
        Result<Audition> result = new Result<>();

        if (audition == null) {
            result.addMessage("Auditions cannot be null.", ResultType.INVALID);
            return result;
        }

        List<Audition> all = repository.findAll();
        for (Audition a : all) {
            if (a.getAuditioneeId() == audition.getAuditioneeId() && a.getPartId() == audition.getPartId()) {
                result.addMessage("Duplicate Auditions are not allowed.", ResultType.INVALID);
            }
        }

        return result;
    }
}
