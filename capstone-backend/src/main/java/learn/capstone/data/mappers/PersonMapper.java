package learn.capstone.data.mappers;

import learn.capstone.models.Person;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;

public class PersonMapper implements  RowMapper<Person>{
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {

        Person user = new Person(resultSet.getInt("person_id"),
                resultSet.getInt("app_user_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"));
        return user;
    }
}
