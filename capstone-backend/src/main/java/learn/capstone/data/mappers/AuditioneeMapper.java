package learn.capstone.data.mappers;

import learn.capstone.models.Auditionee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditioneeMapper implements RowMapper<Auditionee> {

    @Override
    public Auditionee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auditionee auditionee = new Auditionee();
        auditionee.setAuditioneeId(rs.getInt("auditionee_id"));
        auditionee.setAppUserId(rs.getInt("app_user_id"));
        auditionee.setPartId(rs.getInt("part_id"));
        auditionee.setTimeSlot(rs.getString("time_slot"));
        auditionee.setSelection(rs.getString("selection"));
        return auditionee;
    }
}
