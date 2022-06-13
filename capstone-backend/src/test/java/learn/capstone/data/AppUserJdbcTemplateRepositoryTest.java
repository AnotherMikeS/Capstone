package learn.capstone.data;


import learn.capstone.TestHelper;
import learn.capstone.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserJdbcTemplateRepositoryTest {
    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<AppUser> users = repository.findAll();
        assertNotNull(users);
        //If running all at the same time, sometimes it runs after delete, so it's 3. If running by itself, it's 4.
        assertTrue(users.size() == 3 || users.size() == 4);
    }

    @Test
    void shouldFindById() {
        AppUser expected = new AppUser(1, "sNixon", "Shelley", "Nixon", "password");
        AppUser actual = repository.findById(1);
        assertEquals(expected.getAppUserId(), actual.getAppUserId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getPassword(), actual.getPassword());
    }

    @Test
    void shouldNotFindOOB() {
        AppUser actual = repository.findById(1000);
        assertNull(actual);
    }

    @Test
    void shouldAddValid() {
        //Any data that makes it to this later should be valid, so only testing 'shouldAdd', not 'shouldNotAdd'

        AppUser expected = TestHelper.makeUser();
        AppUser actual = repository.add(expected);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate(){
        //Same thing. Any data that makes it here should already be valid

        AppUser updated = new AppUser(2, "jSmith", "John", "Smith", "Password");
        assertTrue(repository.update(updated));
        updated = new AppUser(2, "jJones", "John", "Jones", "Password");
        assertTrue(repository.update(updated));
        updated = new AppUser(2, "jSmith", "James", "Smith", "Password");
        assertTrue(repository.update(updated));
    }


    @Test
    void shouldDelete() {
        assertFalse(repository.deleteById(1000));
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }
}