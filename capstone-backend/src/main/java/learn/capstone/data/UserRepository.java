package learn.capstone.data;

import learn.capstone.models.User;
import java.util.List;

public interface UserRepository {
        List<User> findAll();
        User findById();
        User add(User user);
        User update(User user);
        boolean deleteById(int id);
}
