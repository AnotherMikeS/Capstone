package learn.capstone.domain;

import learn.capstone.TestHelper;
import learn.capstone.data.AuditioneeRepository;
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
        assertEquals(expected.getMessages(), actual.getMessages());
    }

    @Test
    void shouldUpdateExisting() {
        when(repository.update(any())).thenReturn(true);
        Result expected = TestHelper.makeResult();
        Result actual = service.update(TestHelper.makeValidAuditionee7());
        assertEquals(expected.getMessages(), actual.getMessages());
    }


    @Test
    void shouldNotUpdateToNullAuditionee() {
        Auditionee auditionee = null;
        Result expected = TestHelper.makeResult("auditionee cannot be null");
        Result actual = service.update(auditionee);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateToAuditioneeIdLessThanZero() {
        Auditionee auditionee = TestHelper.makeInvalidAuditionee1();
        auditionee.setAuditioneeId(0);
        Result expected = TestHelper.makeResult("auditionee ID is required in order to update");
        Result actual = service.update(auditionee);
        assertEquals(expected.getMessages(), actual.getMessages());
    }


    @Test
    void shouldNotUpdateToNullTimeslot() {
        Auditionee auditionee = TestHelper.makeInvalidAuditionee1();
        auditionee.setTimeSlot(null);
        Result expected = TestHelper.makeResult("a timeslot must be selected");
        Result actual = service.update(auditionee);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotUpdateToNullSelection() {
        Auditionee auditionee = TestHelper.makeInvalidAuditionee1();
        auditionee.setSelection(null);
        Result expected = TestHelper.makeResult("you must enter the piece you will perform");
        Result actual = service.update(auditionee);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateToAppUserIdLessThanZero() {
        Auditionee auditionee = TestHelper.makeInvalidAuditionee1();
        auditionee.setAppUserId(0);
        Result expected = TestHelper.makeResult("app user ID must be greater than 0");
        Result actual = service.update(auditionee);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateToPartIdNotBetween1and2() {
        Auditionee auditionee = TestHelper.makeInvalidAuditionee1();
        auditionee.setPartId(30);
        Result expected = TestHelper.makeResult("part ID must be between 1-2");
        Result actual = service.update(auditionee);
        assertEquals(expected, actual);
    }
}
