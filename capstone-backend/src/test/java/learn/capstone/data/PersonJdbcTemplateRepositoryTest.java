package learn.capstone.data;


import learn.capstone.TestHelper;
import learn.capstone.models.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PersonJdbcTemplateRepositoryTest {
    @Autowired
    PersonJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Person> users = repository.findAll();
        assertNotNull(users);
        //If running all at the same time, sometimes it runs after delete, so it's 3. If running by itself, it's 4.
        assertTrue(users.size() == 4 || users.size() == 5);
    }

    @Test
    void shouldFindById() {
        Person expected = TestHelper.makeMike();
        Person actual = repository.findById(2);
        assertEquals(expected.getPersonId(), actual.getPersonId());
        assertEquals(expected.getAppUserId(), actual.getAppUserId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());

    }

    @Test
    void shouldNotFindById() {
        Person actual = repository.findById(1000);
        assertNull(actual);
    }

    @Test
    void shouldAddValid() {
        //Any data that makes it to this later should be valid, so only testing 'shouldAdd', not 'shouldNotAdd'

        Person expected = TestHelper.makeLuigi();
        Person actual = repository.add(expected);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate(){
        //Same thing. Any data that makes it here should already be valid

        Person updated = new Person(1, 1, "John", "Smith");
        assertTrue(repository.update(updated));
        updated = new Person(1, 1, "John", "Jones");
        assertTrue(repository.update(updated));
        updated = new Person(1, 1, "James", "Smith");
        assertTrue(repository.update(updated));
    }


    @Test
    void shouldDelete() {
        assertFalse(repository.deleteById(1000));
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }
}