package com.example.FeatureTracker;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

//    @Test
//    public void postResponse() throws Exception {
//
//        List<FeatureFlag> featureFlags = new ArrayList<>();
//        FeatureFlag featureFlag = new FeatureFlag();
//        featureFlag.setName("testFeature");
//        //lets use a linked hash map since order is so important for this bit map to work
//        //Map<String, Integer> regions = new LinkedHashMap <>();
//        List<FFRegion> regions = new LinkedList<>();
//        // I assume a list of regions with the defined bitmap order must exist, sooo lets pretend I got this from some call.
//        List<String> regionNames = new ArrayList<>(Arrays.asList( "Asia","Korea", "Europe", "Japan", "America"));
//
//        for( int i =0; i < regionNames.size(); i++) {
//            //regions.put(regionNames.get(i), Character.getNumericValue(bitMap.charAt(i)));
//
//            FFRegion region = new FFRegion();
//            region.setRegionName(regionNames.get(i));
//            region.setRegionValue(1);
//            regions.add(region);
//        }
//
//        featureFlag.setRegionValues(regions);
//        featureFlags.add(featureFlag);
//
//        ResponseEntity<String> response = template.postForEntity("/save-features",featureFlags ,String.class);
//        assertThat(response.getBody()).isEqualTo(JSONArray.toJSONString(featureFlags));
//    }
}
