package learn.capstone.data;

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

    }
}