package com.example.FeatureTracker;

public class FeatureFlag {
    // {name: "useAwesomeGames", regionValues:{Asia:1,Korea:1,Eurpoe:1, Japan:1,America:1}},
    private String name;
    private FFRegions regionValues;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FFRegions getRegionValues() {
        return regionValues;
    }

    public void setRegionValues(FFRegions regionValues) {
        this.regionValues = regionValues;
    }
}
