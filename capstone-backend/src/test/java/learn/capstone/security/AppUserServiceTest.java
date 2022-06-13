package learn.capstone.security;

import learn.capstone.data.AppUserRepository;
import learn.capstone.security.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @MockBean
    AppUserRepository repository;

    @Test
    void shouldFindAll(){

    }

    @Test
    void shouldFindById(){

    }

    @Test
    void shouldNotFindByID(){

    }

    @Test
    void shouldAddValid(){

    }

    @Test
    void shouldNotAddInvalid(){
        //Null Fields
        //Id != 0;

    }

    @Test
    void shouldUpdateValid(){

    }

    @Test
    void shouldNotUpdateInvalid(){
        //Null Fields
        //Id <= 0;

    }

    @Test
    void shouldDelete(){

    }

    @Test
    void shouldNotDelete(){

    }

}