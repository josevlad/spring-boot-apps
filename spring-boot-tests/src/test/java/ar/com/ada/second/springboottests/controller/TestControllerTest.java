package ar.com.ada.second.springboottests.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldBeInBodyOkAnswer() {
        // GIVEN
        String url = "http://localhost:" + port + "/test/one";
        Map<String, String> bodyExpected = new HashMap<>();
        bodyExpected.put("status", "ok");

        // WHEN
        Map body = testRestTemplate.getForObject(url, Map.class);

        // THEN
        assertEquals(bodyExpected, body);
    }

    @Test
    public void shouldBeStatusCodeOkAnswer() throws Exception {
        // GIVEN
        String url = "http://localhost:" + port + "/test/two";

        // WHEN
        mockMvc
            .perform(MockMvcRequestBuilders.get(url))
            // THEN
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

}