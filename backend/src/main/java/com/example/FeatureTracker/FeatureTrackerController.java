package com.example.FeatureTracker;

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

import java.util.*;

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
                featureFlag.setName(featureName);
                //lets use a linked hash map since order is so important for this bit map to work
                //Map<String, Integer> regions = new LinkedHashMap <>();
                List<FFRegion> regions = new LinkedList<>();
                // I assume a list of regions with the defined bitmap order must exist, sooo lets pretend I got this from some call.
                List<String> regionNames = new ArrayList<>(Arrays.asList( "Asia","Korea", "Europe", "Japan", "America"));

                for( int i =0; i < regionNames.size(); i++) {
                    //regions.put(regionNames.get(i), Character.getNumericValue(bitMap.charAt(i)));

                    FFRegion region = new FFRegion();
                    region.setRegionName(regionNames.get(i));
                    region.setRegionValue(Character.getNumericValue(bitMap.charAt(i)));
                    regions.add(region);
                }

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

