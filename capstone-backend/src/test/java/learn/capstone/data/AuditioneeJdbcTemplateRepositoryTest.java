package learn.capstone.data;

import learn.capstone.TestHelper;
import learn.capstone.models.Auditionee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AuditioneeJdbcTemplateRepositoryTest {

    @Autowired
    AuditioneeJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Auditionee> auditionees = repository.findAll();
        assertNotNull(auditionees);
        assertTrue(auditionees.size() == 2 || auditionees.size() == 3);
    }

    @Test
    void shouldFindById() {
        Auditionee expected = new Auditionee(1, 1, 2, "2022-07-01 12:00pm", "Kristin Monologue");
        Auditionee actual = repository.findById(1);
        assertEquals(expected.getAuditioneeId(), actual.getAuditioneeId());
        assertEquals(expected.getAppUserId(), actual.getAppUserId());
        assertEquals(expected.getPartId(), actual.getPartId());
        assertEquals(expected.getTimeSlot(), actual.getTimeSlot());
        assertEquals(expected.getSelection(), actual.getSelection());
    }

    @Test
    void shouldNotFindMissingById() {
        Auditionee actual = repository.findById(5000);
        assertNull(actual);
    }

    @Test
    void shouldAddValid() {
        Auditionee expected = TestHelper.makeValidAuditionee();
        Auditionee actual = repository.add(expected);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateExisting() {
        Auditionee updated = new Auditionee(1, 1, 2, "2022-07-01 12:00pm", "Lady Macbeth Monologue");
        assertTrue(repository.update(updated));
        updated = new Auditionee(2, 2, 2, "2022-07-02 1:00pm", "Hamlet monologue");
        assertTrue(repository.update(updated));
        updated = new Auditionee(1, 1, 2, "2022-07-02 12:00pm", "Puck monologue");
        assertTrue(repository.update(updated));
    }

    @Test
    void shouldNotUpdateMissing() {
        Auditionee notUpdated = new Auditionee(2000, 2000, 2, "2022-07-01 12:00pm", "Lady Macbeth Monologue");
        assertFalse(repository.update(notUpdated));
    }

    @Test
    void shouldDeleteExisting() {
            assertTrue(repository.deleteById(3));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(5000));
    }

}
