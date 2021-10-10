package com.example.FeatureTracker;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FeatureTrackerController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/features")
    @ResponseBody
    public List<FeatureFlag> getFeatures() {


        List<FeatureFlag> featureFlags = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            //TODO create an actual response for this
            String getRequest = Unirest.get("http://localhost:12300/featureflags").asString().getBody();
            JSONArray features = (JSONArray)parser.parse(getRequest);

            for (Object feature : features) {

                JSONObject object = (JSONObject) feature;
                String featureName = object.getAsString("name");
                Integer featureValue = (int)(long)object.getAsNumber("value");
                //TODO valid featureValue simple check 0-31 ? for the current number of regions

                //there is for sure a waaay better way to do this..
                String bitMap =  String.format("%5s", Integer.toBinaryString(featureValue)).replace(' ', '0');
                FeatureFlag featureFlag = new FeatureFlag();
                FFRegions regions = new FFRegions();
                featureFlag.setName(featureName);
                regions.setAsia(Character.getNumericValue(bitMap.charAt(0)));
                regions.setKorea(Character.getNumericValue(bitMap.charAt(1)));
                regions.setEurpoe(Character.getNumericValue(bitMap.charAt(2)));
                regions.setJapan(Character.getNumericValue(bitMap.charAt(3)));
                regions.setAmerica(Character.getNumericValue(bitMap.charAt(4)));
                featureFlag.setRegionValues(regions);

                featureFlags.add(featureFlag);
            }


        } catch (UnirestException | ParseException e){
            //TODO problem calling the FFservice is it running is it sending bad data?
        }
        //TODO edit response to actually use a response object to return error handling messages and status code
        return featureFlags;
    }

    //TODO call the post with data from end
    @PostMapping("/features")
    public String postFeatures() {

        //TODO validation checks if any




        return "writing JSON";
    }

}

