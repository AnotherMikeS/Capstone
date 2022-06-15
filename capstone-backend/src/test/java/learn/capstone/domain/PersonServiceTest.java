package learn.capstone.domain;

import learn.capstone.TestHelper;
import learn.capstone.data.PersonRepository;
import learn.capstone.models.Person;
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
class PersonServiceTest {

    @Autowired
    PersonService service;

    @MockBean
    PersonRepository repository;

    @Test
    void shouldFindAll(){
        List<Person> expected = new ArrayList<>();
        expected.add(TestHelper.makeLuigi());

        when(repository.findAll()).thenReturn(expected);

        List<Person> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById(){
        Person expected = TestHelper.makeMike();

        when(repository.findById(expected.getAppUserId())).thenReturn(expected);

        Person actual = service.findById(expected.getAppUserId());

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByID(){
        assertFalse(repository.findById(1000) != null);
    }

    @Test
    void shouldAddValid(){
        Person postAdd = TestHelper.makeLuigi();

        when(repository.add(any())).thenReturn(postAdd);

        Result expected = TestHelper.makeResult();

        expected.setPayload(postAdd);

        Person preAdd = TestHelper.makeLuigi();
        preAdd.setPersonId(0);

        Result actual = service.add(preAdd);

        assertEquals(actual, expected);
    }

    @Test
    void shouldNotAddInvalid(){
        //Null Fields
        //Id != 0;
        Person allWrong = new Person(1, 0, "", "");

        Result expected = TestHelper.makeResult(
                "First name is required",
                "Last name is required",
                "Valid User Id is required",
                "Person ID must be 0 when adding a person");

        Result actual = service.add(allWrong);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdateValid(){
        when(repository.update(any())).thenReturn(true);

        Result expected = TestHelper.makeResult();
        Person updated = TestHelper.makeMike();
        updated.setFirstName("Matt");
        Result actual = service.update(updated);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalid(){
        //Null Fields
        //Id <= 0;
        Person allWrong = new Person(0, 0, "", "");

        Result expected = TestHelper.makeResult(
                "First name is required",
                "Last name is required",
                "Valid User Id is required",
                "Person ID is required to update a person");

        Result actual = service.update(allWrong);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDelete(){
        when(repository.deleteById(anyInt())).thenReturn(true);
        Result expected = TestHelper.makeResult();
        Result actual = service.deleteById(5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete(){
        Result expected = TestHelper.makeResult("Unable to delete Person.");
        Result actual = service.deleteById(1000);
        assertEquals(expected, actual);
    }

}