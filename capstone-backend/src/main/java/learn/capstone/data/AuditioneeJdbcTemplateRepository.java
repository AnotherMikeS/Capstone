package learn.capstone.data;

import learn.capstone.data.mappers.AuditioneeMapper;
import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AuditioneeJdbcTemplateRepository implements AuditioneeRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuditioneeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Auditionee> findAll() {
     //   String sql = "select * from auditionee;";
        String sql = "select auditionee_id, app_user_id, part_id, time_slot, selection "
                + "from auditionee;";
        return jdbcTemplate.query(sql, new AuditioneeMapper());
    }

    @Override
    public Auditionee findById(int auditioneeId) {
        String sql = "select auditionee_id, app_user_id, part_id, time_slot, selection "
                + "from auditionee "
                + "where auditionee_id = ?;";

        Auditionee auditionee = jdbcTemplate.query(sql, new AuditioneeMapper(), auditioneeId).stream()
                .findFirst()
                .orElse(null);

        return auditionee;
    }

    @Transactional
    @Override
    public Auditionee add(Auditionee auditionee) {

        String sql = "insert into auditionee (app_user_id, part_id, time_slot, selection) values (?,?,?,?);";

        List<Auditionee> all = findAll();
        int max = 1;
        if (all.size() < 1) {
            jdbcTemplate.update("alter table auditions auto_increment = 0;");
        } else {
            max = all.get(0).getAuditioneeId();
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getAuditioneeId() > max) {
                    max = all.get(i).getAuditioneeId();
                }
            }
        }
        jdbcTemplate.update("alter table auditionee auto_increment = ?;", max);

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update((conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, auditionee.getAppUserId());
            statement.setInt(2, auditionee.getPartId());
            statement.setString(3, auditionee.getTimeSlot());
            statement.setString(4, auditionee.getSelection());
            return statement;
        }, keyHolder);

        if (rowsAffected <= 0) {
            auditionee.setAuditioneeId(keyHolder.getKey().intValue());
            return null;

        }
        auditionee.setAuditioneeId(keyHolder.getKey().intValue());
        return auditionee;
    }

    @Transactional
    @Override
    public boolean update(Auditionee auditionee) {

        String sql = "update auditionee set "
                + "auditionee_id = ?, "
                + "app_user_id = ?, "
                + "part_id = ?, "
                + "time_slot = ?, "
                + "selection = ? "
                + "where auditionee_id = ?;";

        return jdbcTemplate.update(sql,
                auditionee.getAuditioneeId(),
                auditionee.getAppUserId(),
                auditionee.getPartId(),
                auditionee.getTimeSlot(),
                auditionee.getSelection(),
       auditionee.getAuditioneeId()) > 0;

    }

//    @Transactional
    @Override
    public boolean deleteById(int auditioneeId) {
        List<Auditionee> auditioneeList = findAll();
        int max = auditioneeList.get(0).getAuditioneeId();
        for (int i = 0; i < auditioneeList.size(); i++) {
            if (auditioneeList.get(i).getAuditioneeId() > max) {
                max = auditioneeList.get(i).getAuditioneeId();
            }
        }
        jdbcTemplate.update("alter table auditionee auto_increment = ?;", max);
        return jdbcTemplate.update("delete from auditionee where auditionee_id = ?;", auditioneeId) > 0;
    }

}
