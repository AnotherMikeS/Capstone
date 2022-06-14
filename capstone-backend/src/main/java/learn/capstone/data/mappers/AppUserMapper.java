//package learn.capstone.data.mappers;
//
//import learn.capstone.models.AppUser;
//import java.sql.ResultSet;
//import org.springframework.jdbc.core.RowMapper;
//import java.sql.SQLException;
//
//public class AppUserMapper implements  RowMapper<AppUser>{
//    @Override
//    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
//
//        AppUser user = new AppUser(resultSet.getInt("app_user_id"),
//                resultSet.getString("username"),
//                resultSet.getString("first_name"),
//                resultSet.getString("last_name"),
//                resultSet.getString("password_hash"));
//        return user;
//    }
//}
