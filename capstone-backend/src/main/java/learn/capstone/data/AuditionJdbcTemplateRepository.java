package learn.capstone.data;

import learn.capstone.data.mappers.AuditionMapper;
import learn.capstone.models.Audition;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class AuditionJdbcTemplateRepository implements AuditionRepository{

    private final JdbcTemplate jdbcTemplate;

    public AuditionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Audition> findAll() {
        final String sql = "select" +
                " auditions.audition_id," +
                " auditionee.auditionee_id," +
                " app_user.first_name," +
                " app_user.last_name," +
                " part.part_id," +
                " part.role" +
                " from app_user" +
                " inner join auditionee on app_user.app_user_id = auditionee.app_user_id" +
                " inner join auditions on auditionee.auditionee_id = auditions.auditionee_id" +
                " inner join part on auditions.part_id = part.part_id;";

        return jdbcTemplate.query(sql, new AuditionMapper());
    }

    @Override
    public Audition findById(int auditionId) {
        final String sql = "select" +
                " auditions.audition_id," +
                " auditionee.auditionee_id," +
                " app_user.first_name," +
                " app_user.last_name," +
                " part.part_id," +
                " part.role" +
                " from app_user" +
                " inner join auditionee on app_user.app_user_id = auditionee.app_user_id" +
                " inner join auditions on auditionee.auditionee_id = auditions.auditionee_id" +
                " inner join part on auditions.part_id = part.part_id" +
                " where audition_id = ?;";

        return jdbcTemplate.query(sql, new AuditionMapper(), auditionId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public Audition add(Audition audition) {
        final String sql = "insert into auditions (auditionee_id, part_id)" +
                " values (?, ?);";

        if (audition.getAuditioneeId() <= 0) {
            return null;
        }

        if (audition.getPartId() > 2) {
            return null;
        }

        List<Audition> all = findAll();
        for (Audition a : all) {
            if ((a.getAuditioneeId() == audition.getAuditioneeId()) && (a.getPartId() == audition.getPartId())) {
                return null;
            }
        }

        KeyHolder keyholder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, audition.getAuditioneeId());
            ps.setInt(2, audition.getPartId());
            return ps;
        }, keyholder);

        if (rowsAffected <= 0) {
            return null;
        }

        audition.setAuditionId(keyholder.getKey().intValue());
        return audition;
    }

    @Override
    public boolean update(Audition audition) {
        final String sql = "update auditions set" +
                " auditionee_id = ?, part_id = ? where audition_id = ?;";

        if (audition.getAuditioneeId() <= 0) {
            return false;
        }

        if (audition.getPartId() > 2) {
            return false;
        }

        List<Audition> all = findAll();
        for (Audition a : all) {
            if ((a.getAuditioneeId() == audition.getAuditioneeId()) && (a.getPartId() == audition.getPartId())) {
                return false;
            }
        }


        return jdbcTemplate.update(sql, audition.getAuditioneeId(), audition.getPartId(),
                audition.getAuditionId()) > 0;
    }

    @Override
    public boolean deleteById(int auditionId) {
        return jdbcTemplate.update("delete from auditions where audition_id = ?", auditionId) > 0;
    }
}
