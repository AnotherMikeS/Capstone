//package learn.capstone.domain;
//
//import learn.capstone.data.AuditioneeRepository;
//import learn.capstone.models.Auditionee;
//import org.springframework.stereotype.Service;
//
//import javax.xml.validation.Validator;
//import java.util.List;
//
//@Service
//public class AuditioneeService {
//
////        [] Auditionee Service
////        - private final AuditioneeRepository
////        - public AuditioneeService(AuditioneeRepository repository)
////        - public List<Auditionee> findAll()
////        - public Auditionee findById()
////        - public Result<Auditionee> add()
////        - public Result<Auditionee> update()
////        - public boolean deleteById()
////        - private Result<Auditionee> validate
//}
//
//    private final AuditioneeRepository repository;
//    private final Validator validator;
//
//    public AuditioneeService(AuditioneeRepository repository, Validator validator) {
//        this.repository = repository;
//        this.validator = validator;
//    }
//
//    public List<Auditionee> findAll() {
//        return repository.findAll();
//    }
//
//    public Auditionee findById(int auditioneeId) {
//        return repository.findById(auditioneeId);
//    }
//
//    public Result<Auditionee> save(Auditionee auditionee) {
//        Result<Auditionee> result = validate(auditionee);
//        if (!result.isSuccess()) {
//            return result;
//        }
//
//        if (auditionee.getAuditioneeId() > 0) {
//            boolean success = repository.update(auditionee);
//            if (!success) {
//                String msg = String.format("auditioneeId %s is not found", auditionee.getAuditioneeId());
//                result.addMessage(msg, ResultType.NOT_FOUND);
//            }
//        } else { //add
//            Auditionee withId = repository.add(auditionee);
//            result.setPayload(withId);
//    }
//
//    private Result validate(Auditionee auditionee) {
//    }
//
//}
