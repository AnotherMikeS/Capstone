package learn.capstone.data;

import learn.capstone.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class AppUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AppUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select app_user_id, username, password_hash, enabled "
                + "from app_user "
                + "where username = ?;";

        return jdbcTemplate.query(sql, new AppUserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "insert into app_user (username, password_hash) values (?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setAppUserId(keyHolder.getKey().intValue());

        updateRoles(user);

        return user;
    }

    @Transactional
    public void update(AppUser user) {

        final String sql = "update app_user set "
                + "username = ?, "
                + "enabled = ? "
                + "where app_user_id = ?";

        jdbcTemplate.update(sql,
                user.getUsername(), !user.isEnabled(), user.getAppUserId());

        updateRoles(user);
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from app_user_role where app_user_id = ?;", user.getAppUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = "insert into app_user_role (app_user_id, app_role_id) "
                    + "select ?, app_role_id from app_role where access_type = ?;";
            jdbcTemplate.update(sql, user.getAppUserId(), role);
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select app_role.access_type "
                + "from app_user_role "
                + "inner join app_role on app_user_role.app_role_id = app_role.app_role_id "
                + "inner join app_user on app_user_role.app_user_id = app_user.app_user_id "
                + "where app_user.username = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("access_type"), username);
    }
}
