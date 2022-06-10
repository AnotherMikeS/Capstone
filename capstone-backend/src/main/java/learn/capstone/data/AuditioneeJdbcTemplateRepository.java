package learn.capstone.data;

import learn.capstone.models.Auditionee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

@Repository
public class AuditioneeJdbcTemplateRepository implements AuditioneeRepository {

    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Auditionee> auditioneeMapper = (rs, rowNum) -> {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(rs.getInt("auditionee_id"));
        auditionee.setUserId(rs.getInt("user_id"));
        auditionee.setPartId(rs.getInt("part_id"));
        auditionee.setSelection(rs.getString("selection"));
        return auditionee;
    };

    public AuditioneeJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Auditionee> findAll() {
        String sql = "select auditionee_id, user_id, part_id, date, selection from auditionee";
        List<Auditionee> auditionees = jdbcTemplate.query(sql, auditioneeMapper);
        return auditionees;
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

    @Transactional
    @Override
    public Auditionee add(Auditionee auditionee) {

        String sql = "insert into auditionee (user_id, part_id, date, selection) values (?,?,?,?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update((conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, auditionee.getUserId());
            statement.setInt(2, auditionee.getPartId());
            statement.setString(3, auditionee.getDate().toString().toLowerCase(Locale.ROOT));
            statement.setString(4, auditionee.getSelection());
            return statement;
        }, keyHolder);

        if (rowsAffected > 0) {
            auditionee.setAuditioneeId(keyHolder.getKey().intValue());
        }

        return null;
    }

    @Transactional
    @Override
    public boolean update(Auditionee auditionee) {

        String sql = "update auditionee set "
                + "user_id = ?, "
                + "part_id = ?, "
                + "date = ? "
                + "selection = ? "
                + "where auditionee_id = ?;";

        int rowsAffected = jdbcTemplate.update(sql,
                auditionee.getAuditioneeId(),
                auditionee.getUserId(),
                auditionee.getPartId(),
                auditionee.getDate(),
                auditionee.getSelection());

        if (rowsAffected > 0) {
            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public boolean deleteById(int auditioneeId) {
        jdbcTemplate.update("delete from auditionee where auditionee_id = ?;", auditioneeId);
        return jdbcTemplate.update("delete from auditionee where auditionee_id = ?;", auditioneeId) > 0;
    }

}
