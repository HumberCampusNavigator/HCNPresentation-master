package com.example.wfg.presentation;

public class sensorReadings {

    String readingsId;
    String readingTime;
    String readingValue;

    public sensorReadings(){


    }

    public sensorReadings(String readingsId, String readingTime, String readingValue) {
        this.readingsId = readingsId;
        this.readingTime = readingTime;
        this.readingValue = readingValue;
    }

    public String getReadingsId() {
        return readingsId;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public String getReadingValue() {
        return readingValue;
    }
}
