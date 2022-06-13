package learn.capstone.domain;

import learn.capstone.TestHelper;
import learn.capstone.data.AuditionRepository;
import learn.capstone.models.Audition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AuditionServiceTest {

    @Autowired
    AuditionService service;

    @MockBean
    AuditionRepository repository;

    @Test
    void shouldFindAll() {
        Audition one = TestHelper.makeAudition1();
        Audition two = TestHelper.makeAudition2();
        Audition three = TestHelper.makeAudition3();

        List<Audition> expected = new ArrayList<>();

        expected.add(one);
        expected.add(two);
        expected.add(three);

        when(repository.findAll()).thenReturn(expected);

        List<Audition> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Audition expected = TestHelper.makeAudition1();

        when(repository.findById(1)).thenReturn(expected);

        Audition actual = service.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissing() {
        assertFalse(repository.findById(1000) != null);
    }

    @Test
    void shouldAddValid() {
        Audition toReturn = TestHelper.makeValidAudition(5);
        when(repository.add(any())).thenReturn(toReturn);

        Result expected = TestHelper.makeResult();
        expected.setPayload(TestHelper.makeValidAudition(5));

        Result actual = service.add(TestHelper.makeValidAudition(0));
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidAuditionee() {
        Audition bad = TestHelper.makeInvalidAuditionee();
        Result expected = TestHelper.makeResult("Auditions must have a valid Auditionee.");
        Result actual = service.add(bad);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidPart() {
        Audition bad = TestHelper.makeInvalidRole();
        Result expected = TestHelper.makeResult("Auditions must have a valid part.");
        Result actual = service.add(bad);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateAudition() {
        Audition one = TestHelper.makeAudition1();
        Audition two = TestHelper.makeAudition2();
        Audition three = TestHelper.makeAudition3();
        List<Audition> all = new ArrayList<>();
        all.add(one);
        all.add(two);
        all.add(three);
        when(repository.findAll()).thenReturn(all);

        Result expected = TestHelper.makeResult("Duplicate Auditions are not allowed.");
        Audition audition = new Audition();
        audition.setAuditionId(4);
        audition.setAuditioneeId(1);
        audition.setPartId(1);

        Result actual = service.add(audition);
        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateExistingValid() {
        when(repository.update(any())).thenReturn(true);
        Result expected = TestHelper.makeResult();
        Result actual = service.update(TestHelper.makeValidAudition(1));
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateExistingToNull() {
        Result expected = TestHelper.makeResult("Auditions cannot be null.");
        Result actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateExistingToInvalidAuditionee() {
        Audition audition = TestHelper.makeValidAudition(0);
        audition.setAuditioneeId(0);
        Result expected = TestHelper.makeResult("Auditions must have a valid Auditionee.");
        Result actual = service.update(audition);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateExistingToInvalidPart() {
        Audition audition = TestHelper.makeValidAudition(0);
        audition.setPartId(5);
        Result expected = TestHelper.makeResult("Auditions must have a valid part.");
        Result actual = service.update(audition);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateMissing() {
        Result expected = TestHelper.makeResult("Audition ID: 1000 was not found.");
        Result actual = service.update(TestHelper.makeValidAudition(1000));
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldNotDeleteExisting() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        Result expected = TestHelper.makeResult();
        Result actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteMissing() {
        Result expected = TestHelper.makeResult("Unable to delete audition.");
        Result actual = service.deleteById(1000);
        assertEquals(expected, actual);
    }
}