package learn.capstone.data;

import learn.capstone.models.Auditionee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
        assertEquals(2, auditionees.size());
    }

    @Test
    void shouldFindById() {
        Auditionee expected = new Auditionee(1, 1, 2, "2022-07-01 12:00pm", "Kristin Monologue");
        Auditionee actual = repository.findById(1);
        assertEquals(expected.getAuditioneeId(), actual.getAuditioneeId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getPartId(), actual.getPartId());
        assertEquals(expected.getTimeSlot(), actual.getTimeSlot());
        assertEquals(expected.getSelection(), actual.getSelection());
    }

    @Test
    void shouldNotFindMissingById() {

    }

    @Test
    void shouldAddValid() {

    }

    @Test
    void shouldNotAddInvalid() {

    }

    @Test
    void shouldUpdateExisting() {

    }

    @Test
    void shouldNotUpdateMissing() {

    }

    @Test
    void shouldDeleteExisting() {

    }

    @Test
    void shouldNotDeleteMissing() {

    }

}
