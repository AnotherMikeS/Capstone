package learn.capstone.data;

import learn.capstone.TestHelper;
import learn.capstone.models.Audition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuditionJdbcTemplateRepositoryTest {

    @Autowired
    AuditionJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Audition> auditions = repository.findAll();
        assertNotNull(auditions);
        assertEquals(1, auditions.get(0).getAuditioneeId());
    }

    @Test
    void shouldFindById() {
        Audition expected = repository.findById(1);
        assertEquals(1, expected.getAuditioneeId());
    }

    @Test
    void shouldNotFindMissing() {
        Audition expected = repository.findById(1000);
        assertNull(expected);
    }

    @Test
    void shouldAddValid() {
        Audition valid = TestHelper.makeAudition3Test();

        repository.add(valid);

        List<Audition> actual = repository.findAll();

        assertEquals(3, actual.size());
    }

    @Test
    void shouldNotAddInvalidAuditionee() {
        Audition bad = TestHelper.makeInvalidAuditionee();

        repository.add(bad);

        List<Audition> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldNotAddInvalidRole() {
        Audition bad = TestHelper.makeInvalidRole();

        repository.add(bad);

        List<Audition> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldNotAddDuplicateAudition() {
        Audition bad = TestHelper.makeInvalidDuplicate();

        repository.add(bad);

        List<Audition> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldUpdateExistingValid() {
        Audition existing = TestHelper.makeAudition3Test();
        repository.add(existing);

        existing.setAuditioneeId(1);
        existing.setPartId(1);

        assertTrue(repository.update(existing));
        assertEquals(existing.getAuditioneeId(), repository.findById(3).getAuditioneeId());
    }

    @Test
    void shouldNotUpdateInvalidRole() {
        Audition existing = TestHelper.makeAudition3Test();
        repository.add(existing);

        existing.setAuditioneeId(1);
        existing.setPartId(5);

        assertFalse(repository.update(existing));
    }

    @Test
    void shouldNotUpdateInvalidAuditionee() {
        Audition existing = TestHelper.makeAudition3Test();
        repository.add(existing);

        existing.setAuditioneeId(0);
        existing.setPartId(2);

        assertFalse(repository.update(existing));
    }

    @Test
    void shouldNotUpdateInvalidDuplicate() {
        Audition existing = TestHelper.makeAudition3Test();
        repository.add(existing);

        existing.setAuditioneeId(2);
        existing.setPartId(1);

        assertFalse(repository.update(existing));
    }

    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteById(1));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(1000));
    }
}