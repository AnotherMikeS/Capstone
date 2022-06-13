package learn.capstone.data;

import learn.capstone.data.mappers.AppUserMapper;
import learn.capstone.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AppUserJdbcTemplateRepository implements AppUserRepository{
    private final JdbcTemplate jdbcTemplate;
    public AppUserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AppUser> findAll() {
        final String sql = "select * from app_user;";

        return jdbcTemplate.query(sql, new AppUserMapper());
    }

    @Override
    public AppUser findById(int appUserId) {
        final String sql = "select * " +
                "from app_user where app_user_id = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(), appUserId)
                .stream().findFirst().orElse(null);
    }

    @Override
    public AppUser add(AppUser appUser) {
        final String sql = "insert into app_user (username, first_name, last_name, password_hash) " +
                "values (?, ?, ?, ?);";

        KeyHolder keyholder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, appUser.getUsername());
            ps.setString(2, appUser.getFirstName());
            ps.setString(3, appUser.getLastName());
            ps.setString(4, appUser.getPassword());
            return ps;
        }, keyholder);

        if (rowsAffected <= 0) {
            return null;
        }

        appUser.setAppUserId(keyholder.getKey().intValue());
        return appUser;
    }

    @Override
    public boolean update(AppUser appUser) {
        final String sql = "update app_user set " +
                "app_user_id = ?, " +
                "username = ?, " +
                "first_name = ?, " +
                "last_name = ?, " +
                "password_hash = ?, " +
                "where user_id = ?;";

        return jdbcTemplate.update(sql,
                appUser.getAppUserId(),
                appUser.getUsername(),
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getPassword(),

                appUser.getAppUserId()) > 0;
    }

    @Override
    public boolean deleteById(int appUserId) {
//        jdbcTemplate.update("delete from manager where user_id = ?", userId);
        //delete from audition inner join audition to auditionee where auditionee.userid = ?
//        jdbcTemplate.update("delete from auditionee where user_id = ?", userId);

        return jdbcTemplate.update("delete from app_user where app_user_id = ?", appUserId) > 0;
    }
}
