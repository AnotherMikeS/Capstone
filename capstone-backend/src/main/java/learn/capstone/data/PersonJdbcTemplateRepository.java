package learn.capstone.data;

import learn.capstone.data.mappers.PersonMapper;
import learn.capstone.models.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PersonJdbcTemplateRepository implements PersonRepository {
    private final JdbcTemplate jdbcTemplate;
    public PersonJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        final String sql = "select * from person;";

        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
    public Person findById(int personId) {
        final String sql = "select * " +
                "from person where person_id = ?;";

        return jdbcTemplate.query(sql, new PersonMapper(), personId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public Person add(Person person) {
        final String sql = "insert into person (person_id, app_user_id, first_name, last_name) " +
                "values (?, ?, ?, ?);";

        KeyHolder keyholder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, person.getPersonId());
            ps.setInt(2, person.getAppUserId());
            ps.setString(3, person.getFirstName());
            ps.setString(4, person.getLastName());
            return ps;
        }, keyholder);

        if (rowsAffected <= 0) {
            return null;
        }

        person.setAppUserId(keyholder.getKey().intValue());
        return person;
    }

    @Override
    public boolean update(Person person) {
        final String sql = "update person set " +
                "person_id = ?, " +
                "app_user_id = ?, " +
                "first_name = ?, " +
                "last_name = ? " +
                "where person_id = ?;";

        return jdbcTemplate.update(sql,
                person.getPersonId(),
                person.getAppUserId(),
                person.getFirstName(),
                person.getLastName(),

                person.getAppUserId()) > 0;
    }

    @Override
    public boolean deleteById(int personId) {
//        jdbcTemplate.update("delete from manager where user_id = ?", userId);
        //delete from audition inner join audition to auditionee where auditionee.userid = ?
//        jdbcTemplate.update("delete from auditionee where user_id = ?", userId);

        return jdbcTemplate.update("delete from person where person_id = ?", personId) > 0;
    }
}
