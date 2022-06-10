package learn.capstone.data;

import learn.capstone.models.Auditionee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuditioneeJdbcTemplateRepository implements AuditioneeRepository {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Auditionee> auditioneeMapper = (rs, rowNum) -> {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(rs.getInt("auditionee_id"));
        auditionee.set
    }


}
