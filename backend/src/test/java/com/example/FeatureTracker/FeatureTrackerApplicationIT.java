package com.example.FeatureTracker;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeatureTrackerApplicationIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void responseValue() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }
    //TODO add actual tests
//    @Test
//    public void featuresResponseValue() throws Exception {
//        ResponseEntity<String> response = template.getForEntity("/features", String.class);
//        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
//    }
}
