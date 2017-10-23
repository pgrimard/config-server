package com.patrickgrimard.configserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigServerApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void configurationAvailable() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/app/cloud", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void envPostAvailable() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = restTemplate.postForEntity(
                "http://localhost:" + port + "/admin/env", form, Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
