package learn.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.capstone.data.AuditionRepository;
import learn.capstone.models.Audition;
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
class AuditionControllerTest {

    @MockBean
    AuditionRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400WhenEmpty() throws Exception {
        var request = post("/api/audition")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400WhenInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Audition audition = new Audition();
        String auditionJson = jsonMapper.writeValueAsString(audition);

        var request = post("/api/audition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(auditionJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn415WhenMultipart() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Audition audition = new Audition(0, 1, 1);
        String auditionJson = jsonMapper.writeValueAsString(audition);

        var request = post("/api/audition")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(auditionJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void addShouldReturn201() throws Exception {
        Audition audition = new Audition(0, 1, 1);
        Audition expected = new Audition(1, 1, 1);

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();

        String auditionJson = jsonMapper.writeValueAsString(audition);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/audition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(auditionJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}