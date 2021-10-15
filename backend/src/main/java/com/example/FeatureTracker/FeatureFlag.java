package com.example.FeatureTracker;

import java.util.List;

public class FeatureFlag {
    // {name: "useAwesomeGames", regionValues:{Asia:1,Korea:1,Eurpoe:1, Japan:1,America:1}},
    private String name;
    //private Map<String, Integer> regionValues;
    private List<FFRegion> regionValues;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FFRegion> getRegionValues() {
        return regionValues;
    }

    public void setRegionValues(List<FFRegion> regionValues) {
        this.regionValues = regionValues;
    }
}
