package learn.capstone.data;

import learn.capstone.models.AppUser;
import java.util.List;

public interface AppUserRepository {
        List<AppUser> findAll();
        AppUser findById(int appUserId);
        AppUser add(AppUser appUser);
        boolean update(AppUser appUser);
        boolean deleteById(int id);
}
