package learn.capstone.data.mappers;

import learn.capstone.models.Audition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditionMapper implements RowMapper<Audition> {
    @Override
    public Audition mapRow(ResultSet rs, int nowNum) throws SQLException {
        Audition audition = new Audition();
        audition.setAuditionId(rs.getInt("audition_id"));
        audition.setAuditioneeId(rs.getInt("auditionee_id"));
        audition.setFirstName(rs.getString("first_name"));
        audition.setLastName(rs.getString("last_name"));
        audition.setPartId(rs.getInt("part_id"));
        audition.setRole(rs.getString("role"));
        return audition;
    }
}
