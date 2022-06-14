package learn.capstone.domain;

import learn.capstone.TestHelper;
import learn.capstone.data.AuditioneeRepository;
import learn.capstone.models.Audition;
import learn.capstone.models.Auditionee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AuditioneeServiceTest {

    @Autowired
    AuditioneeService service;

    @MockBean
    AuditioneeRepository repository;

    @Test
    void shouldFindAll() {
        Auditionee auditionee2 = TestHelper.makeValidAuditionee2();
        Auditionee auditionee3 = TestHelper.makeValidAuditionee3();
        Auditionee auditionee4 = TestHelper.makeValidAuditionee4();

        List<Auditionee> expected = new ArrayList<>();

        expected.add(auditionee2);
        expected.add(auditionee3);
        expected.add(auditionee4);

        when(repository.findAll()).thenReturn(expected);

        List<Auditionee> actual = service.findAll();

        assertEquals(expected, actual);

    }

    @Test
    void shouldFindById() {
        Auditionee expected = TestHelper.makeValidAuditionee5();

        when(repository.findById(6)).thenReturn(expected);

        Auditionee actual = service.findById(6);

        assertEquals(expected, actual);

    }

    @Test
    void shouldNotFindMissingById() {
        assertFalse(repository.findById(5000) != null);
    }

    @Test
    void shouldAddValid() {
        Auditionee toReturn = TestHelper.makeValidAuditionee6();
        when(repository.add(any())).thenReturn(toReturn);

        Result expected = TestHelper.makeResult();
        expected.setPayload(TestHelper.makeValidAuditionee6());

        Result actual = service.add(TestHelper.makeValidAuditionee6());
        assertEquals(expected, actual);

    }

    @Test
    void shouldUpdateExisting() {

    }

    @Test
    void shouldNotUpdateMissing() {

    }
}
