package learn.capstone.data;

import learn.capstone.models.Audition;

import java.util.List;

public interface AuditionRepository {
    List<Audition> findAll();

    Audition findById(int auditionId);

    Audition add(Audition audition);

    boolean update(Audition audition);

    boolean deleteById(int auditionId);
}
