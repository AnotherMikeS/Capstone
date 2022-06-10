package learn.capstone.data;

import learn.capstone.models.AccessType;
import learn.capstone.models.Auditionee;
import learn.capstone.models.Date;
import learn.capstone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static learn.capstone.models.Date.DATE_ONE;
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
        Auditionee expected = new Auditionee(1, 1, 2, Date.DATE_ONE, "Kristin Monologue");
        Auditionee actual = repository.findById(1);
        assertEquals(expected.getAuditioneeId(), actual.getAuditioneeId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getPartId(), actual.getPartId());
        assertEquals(expected.getDate(), actual.getDate());
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
