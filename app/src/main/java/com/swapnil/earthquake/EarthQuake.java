package com.swapnil.earthquake;


import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthQuake {
    private String mLocation;
    private double mMagnitude;
    private long mTimeInMilliseconds;
    private String mUrl;

    public EarthQuake(Double magnitude, String place, long timeInMilliseconds,String url) {
        mMagnitude = magnitude;
        mLocation = place;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl=url;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
    public String getUrl(){
        return mUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }
}
