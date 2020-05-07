package com.swapnil.earthquake;


import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthQuake {
    private String mLocation;
    private double mMagnitude;
    private long mTimeInMilliseconds;

    public EarthQuake(Double magnitude, String place, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = place;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }
}
