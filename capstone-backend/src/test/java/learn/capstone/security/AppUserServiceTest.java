//package learn.capstone.security;
//
//import learn.capstone.App;
//import learn.capstone.TestHelper;
//import learn.capstone.data.AppUserRepository;
//import learn.capstone.domain.Result;
//import learn.capstone.models.AppUser;
//import learn.capstone.security.AppUserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class AppUserServiceTest {
//
//    @Autowired
//    AppUserService service;
//
//    @MockBean
//    AppUserRepository repository;
//
//    @Test
//    void shouldFindAll(){
//        List<AppUser> expected = new ArrayList<>();
//        expected.add(TestHelper.makeUser());
//
//        when(repository.findAll()).thenReturn(expected);
//
//        List<AppUser> actual = service.findAll();
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldFindById(){
//        AppUser expected = TestHelper.makeMike();
//
//        when(repository.findById(expected.getAppUserId())).thenReturn(expected);
//
//        AppUser actual = service.findById(expected.getAppUserId());
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotFindByID(){
//        assertFalse(repository.findById(1000) != null);
//    }
//
//    @Test
//    void shouldFindByUsername(){
//        AppUser expected = TestHelper.makeMike();
//
//        when(repository.findByUsername(expected.getUsername())).thenReturn(expected);
//
//        AppUser actual = service.findByUsername(expected.getUsername());
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotFindByUsername(){
//        assertFalse(repository.findByUsername("Waluigi") != null);
//    }
//
//    @Test
//    void shouldAddValid(){
//        AppUser postAdd = TestHelper.makeUser();
//        postAdd.setAppUserId(6);
//
//        when(repository.add(any())).thenReturn(postAdd);
//
//        Result expected = TestHelper.makeResult();
//        expected.setPayload(postAdd);
//
//        AppUser preAdd = TestHelper.makeUser();
//        preAdd.setAppUserId(0);
//
//        Result actual = service.add(preAdd);
//
//        assertEquals(actual, expected);
//    }
//
//    @Test
//    void shouldNotAddInvalid(){
//        //Null Fields
//        //Id != 0;
//        AppUser allWrong = new AppUser(1, "", "", "", "");
//
//        Result expected = TestHelper.makeResult(
//                "Username is required",
//                "First name is required",
//                "Last name is required",
//                "Password is required",
//                "User ID must be 0 when adding a user");
//
//        Result actual = service.add(allWrong);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldUpdateValid(){
//        when(repository.update(any())).thenReturn(true);
//
//        Result expected = TestHelper.makeResult();
//        AppUser updated = TestHelper.makeMike();
//        updated.setFirstName("Matt");
//        Result actual = service.update(updated);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotUpdateInvalid(){
//        //Null Fields
//        //Id <= 0;
//        AppUser allWrong = new AppUser(0, "", "", "", "");
//
//        Result expected = TestHelper.makeResult(
//                "Username is required",
//                "First name is required",
//                "Last name is required",
//                "Password is required",
//                "User ID is required to update a user");
//
//        Result actual = service.update(allWrong);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldDelete(){
//        when(repository.deleteById(anyInt())).thenReturn(true);
//        Result expected = TestHelper.makeResult();
//        Result actual = service.deleteById(5);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void shouldNotDelete(){
//        Result expected = TestHelper.makeResult("Unable to delete user.");
//        Result actual = service.deleteById(1000);
//        assertEquals(expected, actual);
//    }
//
//}