package learn.capstone.data;

import learn.capstone.models.Auditionee;

import java.util.List;

public interface AuditioneeRepository {

    List<Auditionee> findAll();

    Auditionee findById(int auditioneeId);

    Auditionee add(Auditionee auditionee);

    boolean update(Auditionee auditionee);

    boolean deleteById(int auditioneeId);
}
