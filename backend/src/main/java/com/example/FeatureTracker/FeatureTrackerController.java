package com.example.FeatureTracker;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FeatureTrackerController {

    // I assume a list of regions with the defined bitmap order must exist, sooo lets pretend I got this from some call.
    public static final List<String> regionNames = new ArrayList<>(Arrays.asList( "Asia","Korea", "Europe", "Japan", "America"));

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
            //TODO problem calling the FFservice is it running, is it sending bad data?
        }
        //TODO edit response to actually use a response object to return error handling messages and status code
        return featureFlags;
    }

    //TODO call the post with data from end
    @PostMapping(path="/save-features")
    public ResponseEntity<HttpResponse> postFeatures(@RequestBody List<FeatureFlag> newFeatures) {

        //TODO validation checks if any

        //call post for FFservice for each feature we have
        for( FeatureFlag feature : newFeatures){

            List<FFRegion> regions = feature.getRegionValues();
            StringBuilder  bitMap = new StringBuilder();
            for( int i =0; i < regionNames.size(); i++){
                bitMap.append(regions.get(i).getRegionValue());
            }
            int bitValue = Integer.parseInt(bitMap.toString(),2);

            //TODO could improve FFservice to take a list, or improve our this service to compare features to check for features with a diff
            try {
//                FFServiceReq resBody = new FFServiceReq();
//                resBody.setName(feature.getName());
//                resBody.setValue(bitValue);
//
//                JSONObject resBody2 = new JSONObject();
//                resBody2.put("name", feature.getName());
//                resBody2.put("value", bitValue);

                HttpResponse<JsonNode> jsonResponse
                        = Unirest.post("http://localhost:12300/featureflags")
                        .body("{\"name\":\"string\", \"value\":2}")
                        .asJson();

//                HttpResponse postResponse = Unirest.post("http://localhost:12300/featureflags")
//                        .header("Content-Type", "application/json")
//                        .field("name", feature.getName())
//                        .field("value", bitValue).asJson();


                return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        }


        return null;

    }

}

