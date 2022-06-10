package learn.capstone.data;

import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuditioneeJdbcTemplateRepository implements AuditioneeRepository {

    private final JdbcTemplate jdbcTemplate;

    //ROW MAPPER?

    public AuditioneeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Auditionee> findAll() {
        String sql = "select auditionee_id, user_id, part_id, date, selection from auditionee";
        List<Auditionee> auditionees = jdbcTemplate.query(sql, auditioneeMapper);
    }

    @Override
    public Auditionee findById(int auditioneeId) {
        String sql = "select auditionee_id, user_id, part_id, date, selection "
                + "from auditionee "
                + "where auditionee_id = ?;";

        Auditionee auditionee = jdbcTemplate.query(sql, auditioneeMapper, auditioneeId).stream()
                .findFirst()
                .orElse(null);

       return auditionee;
    }

    @Override
    public Auditionee add(Auditionee auditionee) {
        return null;
    }

    @Override
    public boolean update(Auditionee auditionee) {
        return false;
    }

    @Override
    public boolean deleteById(int auditioneeId) {
        return false;
    }

}
