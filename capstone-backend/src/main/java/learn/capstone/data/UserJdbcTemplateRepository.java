package learn.capstone.data;

import learn.capstone.data.mappers.AuditionMapper;
import learn.capstone.data.mappers.UserMapper;
import learn.capstone.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;
    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select * from user;";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int userId) {
        final String sql = "select *" +
                "from user where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user (access_type, first_name, last_name) " +
                "values (?, ?, ?);";

        KeyHolder keyholder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getAccessType().toString().toLowerCase(Locale.ROOT));
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps;
        }, keyholder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyholder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user set " +
                "user_id = ?, " +
                "access_type = ?, " +
                "first_name = ?, " +
                "last_name = ? " +
                "where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getUserId(),
                user.getAccessType().toString().toLowerCase(Locale.ROOT),
                user.getFirstName(),
                user.getLastName(),

                user.getUserId()) > 0;
    }

    @Override
    public boolean deleteById(int userId) {
//        jdbcTemplate.update("delete from manager where user_id = ?", userId);
        //delete from audition inner join audition to auditionee where auditionee.userid = ?
//        jdbcTemplate.update("delete from auditionee where user_id = ?", userId);

        return jdbcTemplate.update("delete from `user` where user_id = ?", userId) > 0;
    }
}
