package learn.capstone.data;


import learn.capstone.TestHelper;
import learn.capstone.models.AccessType;
import learn.capstone.models.Audition;
import learn.capstone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void shouldFindById() {
        User expected = new User(1, "Shelley", "Nixon", AccessType.AUDITIONEE);
        User actual = repository.findById(1);
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAccessType(), actual.getAccessType());
    }

    @Test
    void shouldNotFindOOB() {
        User actual = repository.findById(1000);
        assertNull(actual);
    }

    @Test
    void shouldAddValid() {
        //Any data that makes it to this later should be valid, so only testing add
        User expected = new TestHelper().makeUser();
        User actual = repository.add(expected);

        assertEquals(expected, actual);
    }
    @Test
    void shouldDelete() {
        assertFalse(repository.deleteById(1000));
        assertTrue(repository.deleteById(4));
        assertFalse(repository.deleteById(4));
    }
}