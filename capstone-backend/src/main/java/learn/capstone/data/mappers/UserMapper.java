package learn.capstone.data.mappers;

import learn.capstone.models.AccessType;
import learn.capstone.models.User;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.util.Locale;

public class UserMapper implements  RowMapper<User>{
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User(resultSet.getInt("user_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                AccessType.valueOf(resultSet.getString("access_type").toUpperCase(Locale.ROOT)));
        return user;
    }
}
