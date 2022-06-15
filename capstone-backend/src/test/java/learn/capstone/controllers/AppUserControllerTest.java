package learn.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.capstone.data.AppUserRepository;
import learn.capstone.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppUserControllerTest {
    @MockBean
    AppUserRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/theater/appUser")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        AppUser user = new AppUser();
        String userJson = jsonMapper.writeValueAsString(user);

        var request = post("/api/theater/appUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn415WhenMultipart() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        AppUser user = new AppUser(0,"testname","test","mcTest","testword");
        String userJson = jsonMapper.writeValueAsString(user);

        var request = post("/api/theater/appUser")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void addShouldReturn201() throws Exception {
        AppUser actual = new AppUser(0,"testname","test","mcTest","testword");
        AppUser expected = new AppUser(5,"testname","test","mcTest","testword");

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();

        String userJson = jsonMapper.writeValueAsString(actual);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/theater/appUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}