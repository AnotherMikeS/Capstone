package learn.capstone.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.capstone.data.AuditioneeRepository;
import learn.capstone.models.Auditionee;
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
public class AuditioneeControllerTest {

    @MockBean
    AuditioneeRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn400IfEmpty() throws Exception {
        var request = post("/api/theater/auditionee")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn400IfInvalid() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Auditionee auditionee = new Auditionee();
        String auditioneeJson = jsonMapper.writeValueAsString(auditionee);

        var request = post("/api/theater/auditionee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(auditioneeJson);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void addShouldReturn415IfMultipart() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Auditionee auditionee = new Auditionee(10, 10, 2, "Controller Test Timeslot", "Controller Test Selection");
        String auditioneeJson = jsonMapper.writeValueAsString(auditionee);

        var request = post("/api/theater/auditionee")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(auditioneeJson);

        mvc.perform(request)
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    void addShouldReturn201() throws Exception {
        Auditionee auditionee = new Auditionee(0, 1, 2, "Test Date", "Test Selection");
        Auditionee expected = new Auditionee(1, 1, 2, "Test Date", "Test Selection");

        when(repository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();

        String auditioneeJson = jsonMapper.writeValueAsString(auditionee);
        String expectedJson = jsonMapper.writeValueAsString(expected);

        var request = post("/api/theater/auditionee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(auditioneeJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }

}
